package cn.wolfcode.crm.web.controller;

import cn.wolfcode.crm.domain.Product;
import cn.wolfcode.crm.mapper.ProductMapper;
import cn.wolfcode.crm.query.ProductQueryObject;
import cn.wolfcode.crm.service.IProductService;
import cn.wolfcode.crm.util.JsonResult;
import cn.wolfcode.crm.util.PageResult;
import cn.wolfcode.crm.util.UploadUtil;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletResponse;
import java.io.InputStream;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.List;

/**
 * Demo class
 *
 * @author user
 * @date yyyy/MM/dd
 */
@Controller
@RequestMapping("product")
public class ProductController {

    @Autowired
    private IProductService productService;
    @Autowired
    private ProductMapper productMapper;
    @Autowired
    private ServletContext context;

    @RequestMapping("view")
    public String view(){
        return "product";
    }

    //商品列表
    @RequestMapping("list")
    @ResponseBody
    public PageResult list(ProductQueryObject qo){

        return productService.query(qo);
    }
    @RequestMapping("get")
    @ResponseBody
    public Product get(Long id){

        return productService.get(id);
    }



    //查询所有列
    @RequestMapping("countId")
    @ResponseBody
    public Object countId(ProductQueryObject qo){

        return productService.listAll();
    }

    //查询所有列
    @RequestMapping("query")
    @ResponseBody
    public Object query(Long id){

        return productService.get(id);
    }


//  <%--post 1 提交表单  enctype  2 编码类型  3 上传控件 controller/ MultipartFile--%>
    //保存的时候路径+名称一起保存 然后就可以直接找到图片
    @RequestMapping("saveOrUpdate")
    @ResponseBody
    public Object saveOrUpdate(Product entity, MultipartFile pic){ //pic 与 input商品图片 name 相同
        JsonResult result = new JsonResult();
        try {
            //删除商品的图片:1用户传递了图片 2:当前商品之前是有图片的
            if(pic != null && !StringUtils.isEmpty(entity.getImagePath())){
                //如果有图片先删除
                UploadUtil.deleteFile(context, entity.getImagePath());
            }
            //保存上传文件 判断一下不为null
            if (pic != null){
                String imagePath = UploadUtil.upload(pic, context.getRealPath("/static/upload"));
                System.out.println(context.getRealPath("/static/upload"));
                //获取到再设置到
                entity.setImagePath(imagePath);
            }
            productService.saveOrUpdate(entity);
        }catch (Exception e){
            e.printStackTrace();
            result.mark("保存失败");
        }
        return result;
    }


    @RequestMapping("save")
    @ResponseBody
    public Object save(Product entity){
        JsonResult jsonResult = new JsonResult();
        try {
            productService.save(entity);
        }catch (Exception e){
            e.printStackTrace();
            jsonResult.mark("操作失败");
        }
        return jsonResult;
    }

    @RequestMapping("delete")
    @ResponseBody
    public Object delete(Long id){
        JsonResult jsonResult = new JsonResult();
        try {
            productService.delete(id);
        }catch (Exception e){
            e.printStackTrace();
            jsonResult.mark("操作失败");
        }
        return jsonResult;
    }

    //导出功能
    @RequestMapping("derive")
    public void exportXls(HttpServletResponse response)throws Exception{
        //设置文件下载响应头
        response.setHeader("Content-Disposition", "attachment;filename=product.xls");
        //创建excel
        Workbook wb = new HSSFWorkbook();
        //创建工作薄
        Sheet sheet = wb.createSheet("day01");
        //设置标题
        Row row = sheet.createRow(0);
        row.createCell(0).setCellValue("用户名");
        row.createCell(1).setCellValue("真实姓名");
        row.createCell(2).setCellValue("邮件");
        row.createCell(3).setCellValue("电话");
        List<Product> productList = productService.listAll();
        for (int i = 1; i < productList.size(); i++) {
            //不-1 会拿不到第一行 因为第一行从0 开始
            Product product = productList.get(i-1);
            //创建行 不能+1
            row = sheet.createRow(i);
            //创建单元格
            // Cell cell = row.createCell(2);
            //设置单元格内容
            //cell.setCellValue("你好");
            if (product.getName() != null){
                row.createCell(0).setCellValue(product.getName());
            }
            if (product.getBrand() != null){
                row.createCell(1).setCellValue(product.getBrand());
            }
            if (product.getAuxiliaryWord() != null){
                row.createCell(2).setCellValue(product.getAuxiliaryWord());
            }
            if (product.getGoodsMark() != null){
                row.createCell(3).setCellValue(product.getGoodsMark());
            }
            if (product.getPurchasingPrice() != null){
                row.createCell(4).setCellValue(product.getPurchasingPrice().toString());
            }
            if (product.getUnitpPrice() != null){
                row.createCell(5).setCellValue(product.getUnitpPrice().toString());
            }
            if (product.getMemberPrice() != null){

            row.createCell(6).setCellValue(product.getMemberPrice().toString());
            }
            if (product.getMinDiscount() != null){
                row.createCell(7).setCellValue(product.getMinDiscount().toString());
            }
            if (product.getMinPrice() != null){
                row.createCell(8).setCellValue(product.getMinPrice().toString());
            }
            if (product.getInitialInventory() != null){
                row.createCell(9).setCellValue(product.getInitialInventory());
            }
            if (product.getSpecification() != null){
                row.createCell(10).setCellValue(product.getSpecification());
            }
            if (product.getUnit() != null){
                row.createCell(11).setCellValue(String.valueOf(product.getUnit()) );
            }
            if (product.getIntegral() != null){
                row.createCell(12).setCellValue(String.valueOf(product.getIntegral()));
            }
            if (product.getCommission() != null){
                row.createCell(13).setCellValue(product.getCommission().toString());
            }
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
            if (product.getPastDueTime() != null){
                row.createCell(14).setCellValue( simpleDateFormat.format(product.getPastDueTime()));
            }
            if (product.getRemark() != null){
                row.createCell(15).setCellValue(product.getRemark());
            }
            if (product.getImagePath() != null){
                row.createCell(16).setCellValue(product.getImagePath());
            }
        }
        //写入数据(输出到浏览器)
        wb.write(response.getOutputStream());
    }

    //导入
    @RequestMapping("tolead")
    public void importXls(MultipartFile file, HttpServletResponse response) throws Exception{
        InputStream inputStream = file.getInputStream();
        //创建excel文件 指定输入流
        HSSFWorkbook wb = new HSSFWorkbook(inputStream);

        Sheet sheet = wb.getSheetAt(0);
        //获取最后一个索引
        int lastRowNum = sheet.getLastRowNum();
        for (int i = 1; i <= lastRowNum; i++) {
            Product product = new Product();
            Row row = sheet.getRow(i);
            try {
                if (row.getCell(0) != null){
                    product.setName(row.getCell(0).getStringCellValue());
                }
                if (row.getCell(1) != null){
                    product.setBrand(row.getCell(1).getStringCellValue());
                }
                if (row.getCell(2) != null){
                    product.setAuxiliaryWord(row.getCell(2).getStringCellValue());
                }
                if (row.getCell(3) != null){
                    product.setGoodsMark(row.getCell(3).getStringCellValue());
                }
                if (row.getCell(4) != null){
                    product.setPurchasingPrice(new BigDecimal(row.getCell(4).getStringCellValue()));
                }
                if (row.getCell(5) != null){
                    product.setUnitpPrice(new BigDecimal(row.getCell(5).getStringCellValue()));
                }
                if (row.getCell(6) != null){
                    product.setMemberPrice(new BigDecimal(row.getCell(6).getStringCellValue()));
                }
                if (row.getCell(7) != null){
                    product.setMinDiscount(new BigDecimal(row.getCell(7).getStringCellValue()));
                }
                if (row.getCell(8) != null){
                    product.setMinPrice(new BigDecimal(row.getCell(8).getStringCellValue()));
                }
                if (row.getCell(9) != null){
                    product.setInitialInventory(row.getCell(9).getStringCellValue());
                }
                if (row.getCell(10) != null){
                    product.setSpecification(row.getCell(10).getStringCellValue());
                }
                    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
                if (row.getCell(11) != null){
                    //product.setUnit(row.getCell(11).getn);
                }
                if (row.getCell(12) != null){
                    product.setIntegral(new Long(row.getCell(12).getStringCellValue()));
                }
                if (row.getCell(13) != null){
                    product.setCommission(new BigDecimal(row.getCell(13).getStringCellValue()));
                }
                if (row.getCell(14) != null){
                    product.setPastDueTime(simpleDateFormat.parse(row.getCell(14).getStringCellValue()));
                }
                if (row.getCell(15) != null){
                    product.setRemark(row.getCell(15).getStringCellValue());
                }
                if (row.getCell(16) != null){
                    product.setImagePath(row.getCell(16).getStringCellValue());
                }
                productService.saveOrUpdate(product);
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }
   /* public Object getContent(Cell cell){
        switch (cell.getCellType()){
            case 1:
                return cell.getRichStringCellValue().getString();
            case 0:
                return cell.getNumericCellValue();
            default:
        }
        return null;
    }
*/
    //查询分类商品列
    @RequestMapping("fruits")
    @ResponseBody
    public Object fruits(Long id){
        return productService.selectByUnitId(1L);
    }
    @RequestMapping("drinks")
    @ResponseBody
    public Object drinks(Long id){
        return productService.selectByUnitId(2L);
    }
    @RequestMapping("foodstuff")
    @ResponseBody
    public Object foodstuff(Long id){
        return productService.selectByUnitId(3L);
    }
    @RequestMapping("vegetables")
    @ResponseBody
    public Object vegetables(Long id){
        return productService.selectByUnitId(4L);
    }
    @RequestMapping("grain")
    @ResponseBody
    public Object grain(Long id){
        return productService.selectByUnitId(5L);
    }
    @RequestMapping("aquatic")
    @ResponseBody
    public Object aquatic(Long id){
        return productService.selectByUnitId(6L);
    }
    @RequestMapping("child")
    @ResponseBody
    public Object child(Long id){
        return productService.selectByUnitId(7L);
    }

}
























