package by.vsu.twoa.service;

import by.vsu.twoa.dao.DaoException;
import by.vsu.twoa.dao.TaskDao;
import by.vsu.twoa.dao.UserDao;
import by.vsu.twoa.domain.Task;
import by.vsu.twoa.domain.User;
import by.vsu.twoa.geometry.Point;
import by.vsu.twoa.geometry.Quadrilateral;
import by.vsu.twoa.geometry.QuadrilateralType;
import by.vsu.twoa.geometry.Segment;
import by.vsu.twoa.service.exception.ServiceException;
import by.vsu.twoa.service.exception.TaskNotExistsException;
import by.vsu.twoa.service.exception.UserNotExistsException;

import java.util.Date;
import java.util.List;

public class TaskService {
	private TaskDao taskDao;
	private UserDao userDao;

	public void setTaskDao(TaskDao taskDao) {
		this.taskDao = taskDao;
	}

	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}

	public List<Task> findByOwner(Integer id) throws ServiceException {
		try {
			User owner = userDao.read(id).orElseThrow(() -> new UserNotExistsException(id));
			List<Task> tasks = taskDao.readByOwner(id);
			tasks.forEach(task -> task.setOwner(owner));
			return tasks;
		} catch(DaoException e) {
			throw new ServiceException(e);
		}
	}

	public Task findById(Integer id) throws ServiceException {
		try {
			Task task = taskDao.read(id).orElseThrow(() -> new TaskNotExistsException(id));
			task.setOwner(userDao.read(task.getOwner().getId()).orElseThrow(() -> new UserNotExistsException(id)));
			Quadrilateral quadrilateral = task.getQuadrilateral();
			Point a = quadrilateral.getVertex1();
			Point b = quadrilateral.getVertex2();
			Point c = quadrilateral.getVertex3();
			Point d = quadrilateral.getVertex4();
			double ab = new Segment(a, b).length();
			double bc = new Segment(b, c).length();
			double cd = new Segment(c, d).length();
			double ad = new Segment(a, d).length();
			if(equal(ab, cd) || equal(bc, ad)) {
				task.setQuadrilateralType(QuadrilateralType.PARALLELOGRAM);
				boolean isRhombus = equal(ab, bc);
				double ac = new Segment(a, c).length();
				double bd = new Segment(b, d).length();
				boolean isRectangle = equal(ac, bd);
				if(isRhombus && isRectangle) {
					task.setQuadrilateralType(QuadrilateralType.SQUARE);
				}
				if(isRectangle) {
					task.setQuadrilateralType(QuadrilateralType.RHOMBUS);
				}
				if(isRhombus) {
					task.setQuadrilateralType(QuadrilateralType.RECTANGLE);
				}
			} else {
				task.setQuadrilateralType(QuadrilateralType.ARBITRARY);
			}
			return task;
		} catch(DaoException e) {
			throw new ServiceException(e);
		}
	}

	public Integer save(Task task) throws ServiceException {
		try {
			if(task.getId() == null) {
				task.setCreated(new Date(0));
				return taskDao.create(task);
			} else {
				throw new RuntimeException("Update operation not implemented yet");
			}
		} catch(DaoException e) {
			throw new ServiceException(e);
		}
	}

	private static boolean equal(double a, double b) {
		return Math.abs(a - b) < 0.0001;
	}
}
