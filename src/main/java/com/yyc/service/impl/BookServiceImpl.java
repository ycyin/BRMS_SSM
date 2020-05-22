package com.yyc.service.impl;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.write.metadata.style.WriteCellStyle;
import com.alibaba.excel.write.metadata.style.WriteFont;
import com.alibaba.excel.write.style.HorizontalCellStyleStrategy;
import com.alibaba.fastjson.JSON;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.yyc.dao.BookCategoryMetaMapper;
import com.yyc.dao.BookDTOMapper;
import com.yyc.dao.BookMapper;
import com.yyc.dao.PublisherMapper;
import com.yyc.dto.BookDTO;
import com.yyc.entity.Book;
import com.yyc.entity.BookCategoryMeta;
import com.yyc.entity.Publisher;
import com.yyc.service.BookService;
import com.yyc.vo.RespMsg;
import com.yyc.vo.ResultEnum;
import com.yyc.vo.request.BookCameraVo;
import com.yyc.vo.request.BookListVo;
import com.yyc.vo.request.SearchAndPageVo;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**************************************
 * @author 尹以操 E-mail:34782655@qq.com
 * @version 创建/修改时间：
 * 类说明:
 ***************************************
 */
@Service("bookService")
public class BookServiceImpl implements BookService {
    private static Logger logger = LoggerFactory.getLogger(BookServiceImpl.class);

    private final BookMapper bookMapper;
    private final BookDTOMapper bookDTOMapper;
    private final BookCategoryMetaMapper bcmMapper;
    private final PublisherMapper pubMapper;

    @Autowired
    public BookServiceImpl(BookMapper bookMapper, BookDTOMapper bookDTOMapper, BookCategoryMetaMapper bcmMapper, PublisherMapper pubMapper) {
        this.bookMapper = bookMapper;
        this.bookDTOMapper = bookDTOMapper;
        this.bcmMapper = bcmMapper;
        this.pubMapper = pubMapper;
    }

    @Override
    public RespMsg getBookList(SearchAndPageVo searchAndPageVo) {
        logger.info("SearchAndPageVo-->>>"+searchAndPageVo);
        Page<Object> page = PageHelper.startPage(searchAndPageVo.getCurrentPage(), searchAndPageVo.getPageSize());
        List<BookDTO> books = this.bookDTOMapper.selectAllBook(searchAndPageVo);
        Map<String,Object> res = new HashMap<>(200);
        res.put("total",page.getTotal());
        res.put("result",books);
        return new RespMsg(ResultEnum.SELECT_SUCCESS,res);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public RespMsg addBook(Book book) {
        Integer res = 0;
        try{
             res = this.bookMapper.insertBook(book);
            return res >= 1 ? new RespMsg(ResultEnum.ADD_SUCCESS,res):
                    new RespMsg(ResultEnum.ADD_FAILD,res);
        }catch (Exception e){
            if(res > 0){
                return new RespMsg(ResultEnum.ADD_SUCCESS,res);
            }
            if(e.getCause().toString().contains("Duplicate")){
                return new RespMsg(ResultEnum.ADD_FAILD_HAS_BOOK_DUPLICATE,res);
            }else{
                return new RespMsg(ResultEnum.ADD_FAILD_UNKNOW,res);
            }
        }

    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public RespMsg addBookByCamera(BookCameraVo book) {
//        String bookCategory = book.getBookCategory().trim();
        String bookPub = book.getBookPub().trim();
        if (StringUtils.isEmpty(bookPub)){
            // 为空，添加失败
            return new RespMsg(ResultEnum.ADD_FAILD);
        }
        // 查询对应的图书类别和出版社数据库中是否存在
//        BookCategoryMeta bookCategoryMeta = bcmMapper.selectByCategoryName(bookCategory);
        Publisher publisher = pubMapper.selectByPubName(bookPub);
//        Integer bcmId = bookCategoryMeta.getId();
        Integer pubId = null;
//        if (bookCategoryMeta == null){
//            // 图书类别不存在,添加入库并查询添加后的ID
//            BookCategoryMeta bcm = new BookCategoryMeta(bookCategory,bookCategory);
//            Integer integer = bcmMapper.insertBookCategoryMeta(bcm);
//            //使用mybatis的selectKey标签可以返回添加后的ID
//            bcmId = bcm.getId();
//        }else{
//            // 图书类别存在，直接获取ID
//            bcmId = bookCategoryMeta.getId();
//        }
        if (publisher == null){
            // 出版社不存在,添加入库并查询添加后的ID
            Publisher pub = new Publisher(bookPub);
            Integer integer = pubMapper.insertPublisher(pub);
            //使用mybatis的selectKey标签可以返回添加后的ID
            pubId = pub.getId();
        }else{
            pubId = publisher.getId();
        }

        if(pubId == null){
            // 为空不能添加图书，直接返回添加失败
            return new RespMsg(ResultEnum.ADD_FAILD);
        }

        Book insetBook = new Book(book.getBookIsbn(),book.getBookName(),book.getBookPrice(),
                book.getBookAuthor(),book.getBookRepertorySize(),pubId,book.getBookCategory());

        return this.addBook(insetBook);
    }

    @Transactional
    @Override
    public RespMsg modifyBookByPrimaryKey(Book book) {
        Integer res = 0;
        try{
            res = this.bookMapper.updateBook(book);
            return res >= 1 ? new RespMsg(ResultEnum.UPDATE_SUCCESS,res):
                    new RespMsg(ResultEnum.UPDATE_FAILD,res);
        }catch (Exception e){
            if(res > 0){
                return new RespMsg(ResultEnum.UPDATE_SUCCESS,res);
            }
            if(e.getCause().toString().contains("Duplicate")){
                return new RespMsg(ResultEnum.UPDATE_FAILD_HAS_BOOK_DUPLICATE,res);
            }else{
                return new RespMsg(ResultEnum.UPDATE_FAILD_UNKNOW,res);
            }
        }
    }

    @Override
    public RespMsg removeBookByPrimaryKey(Integer id) {
        Integer res = this.bookMapper.deleteBookByPrimaryKey(id);
        return res >= 1 ? new RespMsg(ResultEnum.DELETE_SUCCESS,res):
                new RespMsg(ResultEnum.DELETE_FAILD,res);
    }

    @Override
    public RespMsg getBookPressData() {
        List<Map<String,Object>> bookPressData = this.bookMapper.selectBookPressData();
        return new RespMsg(ResultEnum.SELECT_SUCCESS,bookPressData);
    }

    @Override
    public RespMsg getBookCategoryData() {
        List<Map<String, Object>> maps = this.bookMapper.selectBookNumberGroupByCategory();
        return new RespMsg(ResultEnum.SELECT_SUCCESS,maps);
    }

    @Transactional
    @Override
    public RespMsg addBookes(BookListVo bookListVo) {
        Integer res = 0;
        try{
            List<Book> bookList = new ArrayList<>( );
            bookListVo.getBookList().forEach(jsonStr -> bookList.add(JSON.parseObject(jsonStr, Book.class)));
            res = this.bookMapper.insertBookForeach(bookList);
            return new RespMsg(ResultEnum.IMPORT_SUCCESS,res);
        }catch (Exception e){
            if(res > 0){
                return new RespMsg(ResultEnum.IMPORT_SUCCESS,res);
            }
            if(e.getCause().toString().contains("Duplicate")){
                return new RespMsg(ResultEnum.IMPORT_FAILD_HAS_DUPLICATE,res);
            }else{
                return new RespMsg(ResultEnum.IMPORT_FAILD_UNKNOW,res);
            }

        }

    }

    @Override
    public void exportAllBooksExcel(HttpServletResponse response) throws IOException  {
        String fileName = "图书列表信息.xlsx";
        String sheetName= "图书信息";
        List<BookDTO> books = this.bookDTOMapper.selectAllBook(null);
        // 这里注意 有同学反应使用swagger 会导致各种问题，请直接用浏览器或者用postman
        try {
            response.setContentType("application/vnd.ms-excel");
            response.setCharacterEncoding("utf-8");
            // 这里URLEncoder.encode可以防止中文乱码 当然和easyexcel没有关系
            fileName = URLEncoder.encode(fileName, "UTF-8");
            // 前后端分离跨域时，前端axios无法获取后端自定义Header信息，需要加上下面这句,参考：https://blog.csdn.net/qq_35393869/article/details/88345841
            response.setHeader("Access-Control-Expose-Headers","Content-fileName");
            response.setHeader("Content-fileName", fileName);
            // 这里需要设置不关闭流
//            EasyExcel.write(response.getOutputStream(), BookDTO.class).autoCloseStream(Boolean.FALSE).sheet("模板")
//                    .doWrite(books);
            // 头的策略
            WriteCellStyle headWriteCellStyle = new WriteCellStyle();
            // 背景设置为红色
            headWriteCellStyle.setFillForegroundColor(IndexedColors.SKY_BLUE.getIndex());
            WriteFont headWriteFont = new WriteFont();
            headWriteFont.setFontHeightInPoints((short)12);
            headWriteCellStyle.setWriteFont(headWriteFont);
            headWriteCellStyle.setShrinkToFit(true);
            headWriteCellStyle.setLocked(true);
            // 内容的策略,这里没设置
            WriteCellStyle contentWriteCellStyle = new WriteCellStyle();
            // 这个策略是 头是头的样式 内容是内容的样式 其他的策略可以自己实现
            HorizontalCellStyleStrategy horizontalCellStyleStrategy =
                    new HorizontalCellStyleStrategy(headWriteCellStyle,contentWriteCellStyle);

            EasyExcel.write(response.getOutputStream(), BookDTO.class)
                    .autoCloseStream(Boolean.FALSE)
                    .registerWriteHandler(horizontalCellStyleStrategy)
                    .sheet(sheetName).doWrite(books);
        } catch (Exception e) {
            response.setContentType("application/json");
            response.setCharacterEncoding("utf-8");
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            response.getWriter().println(
                    JSON.toJSONString(new RespMsg(ResultEnum.EXPORT_FILE_FAILED)));
        }
    }

}
