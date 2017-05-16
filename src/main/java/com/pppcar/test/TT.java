package com.pppcar.test;

import java.io.Writer;

import com.pppcar.pojo.Article;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.core.util.QuickWriter;
import com.thoughtworks.xstream.io.HierarchicalStreamWriter;
import com.thoughtworks.xstream.io.xml.PrettyPrintWriter;
import com.thoughtworks.xstream.io.xml.XppDriver;

public class TT {
	public static void main(String[] args) {
		Article article = new Article();
		article.setDescription("111");
		article.setPicUrl("222");
		article.setTitle("222");
		article.setUrl("222");
		xtream.alias("xml", article.getClass());
		String s = xtream.toXML(article);
		System.out.println(s);
	}

	private static XStream xtream = new XStream(new XppDriver() {
		public HierarchicalStreamWriter createWriter(Writer out) {
			return new PrettyPrintWriter(out) {
				protected void writeText(QuickWriter writer, String text) {
						writer.write("<![CDATA[");
						writer.write(text);
						writer.write("]]>");
				}
			};
		}

	});

}
