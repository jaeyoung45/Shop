package com.shop.controller;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.List;

import javax.annotation.Resource;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.shop.domain.CategoryVO;
import com.shop.domain.GoodsVO;
import com.shop.domain.GoodsViewVO;
import com.shop.domain.OrderListVO;
import com.shop.domain.OrderVO;
import com.shop.domain.ReplyListVO;
import com.shop.service.AdminService;
import com.shop.util.MediaUtils;
import com.shop.util.UploadFileUtils;

import net.sf.json.JSONArray;

@Controller
@RequestMapping("/admin/*")
public class AdminController {

	private static final Logger logger = LoggerFactory.getLogger(AdminController.class);

	@Resource(name = "uploadPath")
	String uploadPath;

	@Inject
	AdminService adminService;

	// 관리자화면
	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public void getIndex() throws Exception {
		logger.info("get index");
	}

	// 상품 등록
	@RequestMapping(value = "/goods/register", method = RequestMethod.GET)
	public void getGoodsRegister(Model model) throws Exception {
		logger.info("get goods register");

		List<CategoryVO> category = adminService.category();
		model.addAttribute("category", JSONArray.fromObject(category));

	}

	@RequestMapping(value = "/goods/register", method = RequestMethod.POST)
	public String postGoodsRegister(MultipartFile file, HttpServletRequest request) throws Exception {
		String gdsName = request.getParameter("gdsName");
		String cateCode = request.getParameter("cateCode");
		int gdsPrice = Integer.parseInt(request.getParameter("gdsPrice"));
		int gdsStock = Integer.parseInt(request.getParameter("gdsStock"));
		String gdsDes = request.getParameter("gdsDes");
		String gdsImg = null;
		GoodsVO vo = null;

		if (file != null && (file.getOriginalFilename() != null && file.getOriginalFilename() != ""))
			gdsImg = UploadFileUtils.uploadFile(file.getOriginalFilename(), uploadPath, file.getBytes());

		if (gdsImg != null) {
			vo = new GoodsVO(gdsName, cateCode, gdsPrice, gdsStock, gdsDes, gdsImg);
		} else {
			vo = new GoodsVO(gdsName, cateCode, gdsPrice, gdsStock, gdsDes);
		}
		System.out.println("vo : " + vo);
		adminService.register(vo);

		return "redirect:/admin/index";
	}

	// 상품 목록
	@RequestMapping(value = "/goods/list", method = RequestMethod.GET)
	public void getGoodsList(Model model) throws Exception {
		logger.info("get goods list");

		List<GoodsViewVO> list = adminService.goodslist();

		model.addAttribute("list", list);
	}

	// 상품 목록
//	@RequestMapping(value = "/goods/view", method = RequestMethod.GET)
//	public void goodGoodsview() throws Exception {
//		logger.info("get goods view");
//	}

	// 상품 조회
	@RequestMapping(value = "/goods/view", method = RequestMethod.GET)
	public void getGoodsview(@RequestParam("n") int gdsNum, Model model) throws Exception {
		logger.info("get goods view");

		GoodsViewVO goods = adminService.goodsView(gdsNum);

		String gdsImg = goods.getGdsImg();
		String f = gdsImg.substring(0, gdsImg.indexOf("s_"));
		String l = gdsImg.substring(gdsImg.indexOf("s_") + 2);
		gdsImg = f + l;

		String gdsThumbImg = goods.getGdsImg();

		model.addAttribute("gdsImg", gdsImg);
		model.addAttribute("gdsThumbImg", gdsThumbImg);
		model.addAttribute("goods", goods);
	}

	// 상품 수정
	@RequestMapping(value = "/goods/modify", method = RequestMethod.GET)
	public void getGoodsModify(@RequestParam("n") int gdsNum, Model model) throws Exception {
		logger.info("get goods modify");

		GoodsViewVO goods = adminService.goodsView(gdsNum);
		String gdsImg = goods.getGdsImg();
		String f = gdsImg.substring(0, gdsImg.indexOf("s_"));
		String l = gdsImg.substring(gdsImg.indexOf("s_") + 2);
		gdsImg = f + l;

		String gdsThumbImg = goods.getGdsImg();
		model.addAttribute("goods", goods);
		model.addAttribute("gdsImg", gdsImg);
		model.addAttribute("gdsThumbImg", gdsThumbImg);

		List<CategoryVO> category = null;
		category = adminService.category();
		model.addAttribute("category", JSONArray.fromObject(category));
	}

	// 상품 수정
	@RequestMapping(value = "/goods/modify", method = RequestMethod.POST)
	public String postGoodsModify(MultipartFile file, HttpServletRequest request) throws Exception {
		logger.info("post goods modify");
		int gdsNum = Integer.parseInt(request.getParameter("gdsNum"));
		String gdsName = request.getParameter("gdsName");
		String cateCode = request.getParameter("cateCode");
		int gdsPrice = Integer.parseInt(request.getParameter("gdsPrice"));
		int gdsStock = Integer.parseInt(request.getParameter("gdsStock"));
		String gdsDes = request.getParameter("gdsDes");
		String gdsImg = null;
		GoodsVO vo = null;

		if (file != null && (file.getOriginalFilename() != null && file.getOriginalFilename() != ""))
			gdsImg = UploadFileUtils.uploadFile(file.getOriginalFilename(), uploadPath, file.getBytes());

		if (gdsImg != null) {
			vo = new GoodsVO(gdsNum, gdsName, cateCode, gdsPrice, gdsStock, gdsDes, gdsImg);
		} else {
			vo = new GoodsVO(gdsNum, gdsName, cateCode, gdsPrice, gdsStock, gdsDes);
		}
		System.out.println("vo : " + vo);

		adminService.goodsModify(vo);

		return "redirect:/admin/index";
	}

	// 상품 삭제
	@RequestMapping(value = "/goods/delete", method = RequestMethod.POST)
	public String postGoodsDelete(@RequestParam("n") int gdsNum) throws Exception {
		logger.info("post goods delete");

		adminService.goodsDelete(gdsNum);

		return "redirect:/admin/index";
	}

	@RequestMapping(value = "/displayFile", method = RequestMethod.POST)
	public ResponseEntity<byte[]> displayFile(String fileName) throws Exception {
		logger.info("displayFile Called!!!");
		InputStream in = null;
		ResponseEntity<byte[]> entity = null;

		try {
			String formatName = fileName.substring(fileName.lastIndexOf(".") + 1);
			MediaType mType = MediaUtils.getMediaType(formatName);
			HttpHeaders headers = new HttpHeaders();

			in = new FileInputStream(uploadPath + fileName);

			if (mType != null) {
				headers.setContentType(mType);
			} else {
				fileName = fileName.substring(fileName.indexOf("_") + 1);
				headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
				headers.add("Content-Disposition",
						"attachment;fileName=\"" + new String(fileName.getBytes("UTF-8"), "ISO-8859-1") + "\"");
			}

			entity = new ResponseEntity<byte[]>(IOUtils.toByteArray(in), headers, HttpStatus.CREATED);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			in.close();
		}
		return entity;
	}
	
	// 주문 목록
	@RequestMapping(value = "/shop/orderList", method = RequestMethod.GET)
	public void getOrderList(Model model) throws Exception {
		logger.info("get order list");
		
		List<OrderVO> orderList = adminService.orderList();
		
		model.addAttribute("orderList", orderList);
	}
	
	// 주문 상세 목록
	@RequestMapping(value = "/shop/orderView", method = RequestMethod.GET)
	public void getOrderList(@RequestParam("n") String orderId,
							OrderVO order, Model model) throws Exception {
		logger.info("get order view");
		
		order.setOrderId(orderId);
		List<OrderListVO> orderView = adminService.orderView(order);
		
		model.addAttribute("orderView", orderView);
	}
	
	// 주문 상세 목록 - 상태 변경
	@RequestMapping(value = "/shop/orderView", method = RequestMethod.POST)
	public String delivery(OrderVO order) throws Exception {
		logger.info("post order view");
		
		adminService.delivery(order);
		
		List<OrderListVO> orderView = adminService.orderView(order);
		GoodsVO goods = new GoodsVO();
		
		for(OrderListVO i : orderView) {
			goods.setGdsNum(i.getGdsNum());
			goods.setGdsStock(i.getCartStock());
			adminService.changeStock(goods);;
		}
		
		return "redirect:/admin/shop/orderView?n=" + order.getOrderId();
	}
	
	// 모든 소감(댓글)
	@RequestMapping(value = "/shop/allReply", method = RequestMethod.GET)
	public void getAllReply(Model model) throws Exception {
		logger.info("get all reply");
		
		List<ReplyListVO> reply = adminService.allReply();
		
		model.addAttribute("reply", reply);
	}
	
	// 모든 소감(댓글)
	@RequestMapping(value = "/shop/allReply", method = RequestMethod.POST)
	public String getAllReaply(int repNum) throws Exception {
		logger.info("get all reply");
		
		adminService.deleteReply(repNum);
		return "redirect:/admin/shop/allReply";
	}

}
