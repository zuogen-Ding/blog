package club.banyuan.service;

import club.banyuan.bean.Blog;
import club.banyuan.dao.BlogDao;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BlogService {
    private BlogDao blogDao;

    @Autowired
    public BlogService(BlogDao blogDao) {
        this.blogDao = blogDao;
    }

    public List<Blog> selectBlogsByUsername(String username){
        return blogDao.selectBlogsByUsername(username);
    }

//    public List<Blog> selectBlogsByPageInfo(String username ,Integer page,Integer size){
//        Integer offset =(page-1)*size;
//        return blogDao.selectBlogsByPageInfo(username,offset,size);
//    }
//
//    public Integer selectBlogsCountByUsername(String username){
//        return blogDao.selectBlogsCountByUsername(username);
//    }

    public PageInfo pageUserBlogs(String username,Integer page ,Integer size){
        PageHelper.startPage(page,size);
        List<Blog> userBlogs=blogDao.selectBlogsByUsername(username);
        PageInfo<Blog> blogPageInfo=new PageInfo<>(userBlogs);
        return blogPageInfo;

    }

    public Blog getBlogById(Integer id){
        return blogDao.selectBlogById(id);
    }


    public void addBlog(Blog blog){
        blogDao.insertBlog(blog);
    }
}
