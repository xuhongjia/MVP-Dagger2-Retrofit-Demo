/** * com.upyun.api * UpYunException.java */package com.softstao.softstaolibrary.external.upyun.api.exception;/** * Upyun 异常类 *  * @author wangxiaolong * */public class UpYunException extends Exception {	private static final long serialVersionUID = 3854772125385537971L;		public String message;		public UpYunException(String message) {		super(message);	}}