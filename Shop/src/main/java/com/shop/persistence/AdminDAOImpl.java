package com.shop.persistence;

import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.shop.domain.CategoryVO;
import com.shop.domain.GoodsVO;
import com.shop.domain.GoodsViewVO;
import com.shop.domain.OrderListVO;
import com.shop.domain.OrderVO;
import com.shop.domain.ReplyListVO;

@Repository
public class AdminDAOImpl implements AdminDAO {

	@Inject
	private SqlSession sql;

	// 매퍼
	private static String namespace = "com.shop.mappers.adminMapper";

	// 카테고리
	@Override
	public List<CategoryVO> category() throws Exception {
		return sql.selectList(namespace + ".category");
	}

	// 상품등록
	@Override
	public void register(GoodsVO vo) throws Exception {
		String gdsImg = vo.getGdsImg();

		if (gdsImg != null)
			sql.insert(namespace + ".register", vo);
		else
			sql.insert(namespace + ".registerNull", vo);
	}

	// 상품목록
	@Override
	public List<GoodsViewVO> goodslist() throws Exception {
		// TODO Auto-generated method stub
		return sql.selectList(namespace + ".goodslist");
	}

	// 상품조회 + 카테고리 조인
	@Override
	public GoodsViewVO goodsView(int gdsNum) throws Exception {
		// TODO Auto-generated method stub
		return sql.selectOne(namespace + ".goodsView", gdsNum);
	}

	@Override
	public void goodsModify(GoodsVO vo) throws Exception {
		// TODO Auto-generated method stub
		String gdsImg = vo.getGdsImg();

		if (gdsImg != null)
			sql.update(namespace + ".goodsModify", vo);
		else
			sql.update(namespace + ".goodsModifyNull", vo);
	}

	@Override
	public void goodsDelete(int gdsNum) throws Exception {
		// TODO Auto-generated method stub
		sql.delete(namespace + ".goodsDelete", gdsNum);
	}

	@Override
	public List<OrderVO> orderList() throws Exception {
		// TODO Auto-generated method stub
		return sql.selectList(namespace + ".orderList");
	}

	@Override
	public List<OrderListVO> orderView(OrderVO order) throws Exception {
		// TODO Auto-generated method stub
		return sql.selectList(namespace + ".orderView", order);
	}

	@Override
	public void delivery(OrderVO order) throws Exception {
		// TODO Auto-generated method stub
		sql.update(namespace + ".delivery", order);
	}

	@Override
	public void changeStock(GoodsVO goods) throws Exception {
		// TODO Auto-generated method stub
		sql.update(namespace + ".changeStock", goods);
	}

	@Override
	public List<ReplyListVO> allReply() throws Exception {
		// TODO Auto-generated method stub
		return sql.selectList(namespace + ".allReply");
	}

	@Override
	public void deleteReply(int repNum) throws Exception {
		// TODO Auto-generated method stub
		sql.delete(namespace + ".deleteReply", repNum);
	}

	// 상품조회
//	@Override
//	public GoodsVO goodsView(int gdsNum) throws Exception {
//		// TODO Auto-generated method stub
//		return sql.selectOne(namespace + ".goodsView", gdsNum);
//	}

}
