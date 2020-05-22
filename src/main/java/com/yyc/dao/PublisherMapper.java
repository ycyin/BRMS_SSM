package com.yyc.dao;

import com.yyc.entity.Publisher;
import java.util.List;

/**************************************
 * @author 尹以操 E-mail:34782655@qq.com
 * @version 创建/修改时间：
 * 类说明: 出版社Mapper
 ***************************************
 */
public interface PublisherMapper {
    /**
     * 查询所有出版社
     * @return 出版社信息列表
     */
    List<Publisher> selectAllPublisher();

    /**
     * 根据出版社名查询
     * @param pubName 出版社名
     * @return 出版社信息
     */
    Publisher selectByPubName(String pubName);

    /**
     * 插入出版社信息
     * @param publisher 需要插入的出版社信息
     * @return SQL执行受影响的条数
     */
    Integer insertPublisher(Publisher publisher);
}
