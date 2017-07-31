package com.imooc.service;

import com.imooc.dao.MessageDao;

import java.util.ArrayList;
import java.util.List;

/**
 * 维护service
 */
public class MaintainService {
    private MessageDao messageDao = new MessageDao();

    /**
     * 单个删除
     *
     * @param id 删除文件id
     */
    public void deleteOne(String id) {
        //注意id从String转换成int
        //执行业务逻辑
        if (id != null && !"".equals(id)) {
            messageDao.deleteOne(Integer.valueOf(id));
        }
    }

    public void deleteBatch(String[] ids) {
        MessageDao messageDao = new MessageDao();
        List<Integer> idList = new ArrayList<>();
        for (String id : ids) {
            idList.add(Integer.valueOf(id));
        }
        messageDao.deleteBatch(idList);
    }
}
