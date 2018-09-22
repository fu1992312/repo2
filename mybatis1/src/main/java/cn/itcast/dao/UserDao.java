package cn.itcast.dao;

import cn.itcast.pojo.User;

import java.util.List;

public interface UserDao {
    /**
     * 查询所有
     *
     * @return
     */
    public List<User> findAll();

    /**
     * 根据姓名模糊查询
     *
     * @param name
     * @return
     */
    public List<User> findByName(String name);


    /**
     * 根据uid查询单个用户
     * @param uid
     * @return
     */
    public User findOne(int uid);

    /**
     * 增加用户
     *
     * @param user
     * @return
     */
    public int insert(User user);

    /**
     * 删除用户
     * @param id
     * @return
     */
    public void delete(int id);

    /**
     * 修改用户
     * @param user
     */
    public void update(User user);
}
