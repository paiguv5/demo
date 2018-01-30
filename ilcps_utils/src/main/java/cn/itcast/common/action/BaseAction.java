package cn.itcast.common.action;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

@Controller
@Scope("prototype")

@ParentPackage("struts-default")
public class BaseAction<T> extends ActionSupport implements ModelDriven<T> {

	private T t;

	@Override
	public T getModel() {
		return t;
	}

	/**
	 * 构造方法初始化模型对象
	 */
	public BaseAction() {
		// 或者type对象
		Type type = this.getClass().getGenericSuperclass();
		// 转子类
		ParameterizedType pt = (ParameterizedType) type;
		// 调用方法获取type对象数组
		Type[] types = pt.getActualTypeArguments();
		// 把type对象转子类Class对象
		Class clazz = (Class) types[0];
		// 调用反射创建对象
		try {
			t = (T) clazz.newInstance();
		} catch (InstantiationException | IllegalAccessException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 分页参数
	 */
	private Integer page;
	private Integer rows;

	public Integer getPage() {
		return page;
	}

	public void setPage(Integer page) {
		this.page = page;
	}

	public Integer getRows() {
		return rows;
	}

	public void setRows(Integer rows) {
		this.rows = rows;
	}

	/**
	 * 传入对象转Json后response
	 */
	public void objToJsonResponse(Object obj) {
		// 转Json格式
		String listString = JSON.toJSONString(obj, SerializerFeature.DisableCircularReferenceDetect);
		System.out.println(listString);
		try {
			// 响应浏览器
			HttpServletResponse response = ServletActionContext.getResponse();
			response.setCharacterEncoding("UTF-8");
			response.getWriter().write(listString);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void set(String key,Object obj) {
		ActionContext.getContext().getValueStack().set(key, obj);
	}

	public void push(Object obj) {
		ActionContext.getContext().getValueStack().push(obj);
	}

}
