package com.ratemarkt.sample;

import java.io.IOException;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import com.google.common.base.Charsets;
import com.google.common.io.Resources;
import com.hubspot.jinjava.Jinjava;
import com.hubspot.jinjava.loader.ResourceNotFoundException;

public class TemplateUtil {

	public static void renderTemplate(String templateName, Map<String, Object> context, HttpServletResponse response)
			throws ResourceNotFoundException, IOException {

		Jinjava jinjava = new Jinjava();

		String template = Resources.toString(Resources.getResource(templateName), Charsets.UTF_8);

		String renderedTemplate = jinjava.render(template, context);

		response.getWriter().write(renderedTemplate);
	}
}
