package com.javaex.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.javaex.vo.YysVo;

@Repository
public class YysDao {

	@Autowired
	private SqlSession sqlSession;

	// 리스트 가져오기
	public List<YysVo> coursebookList() {
		System.out.println("YysDao.coursebookList()");

		List<YysVo> coursebookList = sqlSession.selectList("yys.courseList");
		System.out.println(coursebookList);

		return coursebookList;
	}

}
