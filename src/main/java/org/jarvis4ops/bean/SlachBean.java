/**
 * 
 */
package org.jarvis4ops.bean;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author raror3
 *
 */
public class SlachBean {
	private String fallback;
	private String title;
	private String title_link;
	private String text;
	private String image_url;
	private String color = "#800080";
	private String footer;
	private List<String> mrkdwn_in = Arrays.asList("text", "pretext");

	/**
	 * @return the mrkdwn_in
	 */
	public List<String> getMrkdwn_in() {
		return mrkdwn_in;
	}
	/**
	 * @param mrkdwn_in the mrkdwn_in to set
	 */
	public void setMrkdwn_in(List<String> mrkdwn_in) {
		this.mrkdwn_in = mrkdwn_in;
	}
	/**
	 * @return the title_link
	 */
	public String getTitle_link() {
		return title_link;
	}
	/**
	 * @param title_link the title_link to set
	 */
	public void setTitle_link(String title_link) {
		this.title_link = title_link;
	}
	/**
	 * @return the fallback
	 */
	public String getFallback() {
		return fallback;
	}
	/**
	 * @param fallback the fallback to set
	 */
	public void setFallback(String fallback) {
		this.fallback = fallback;
	}
	/**
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}
	/**
	 * @param title the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
	}
	/**
	 * @return the text
	 */
	public String getText() {
		return text;
	}
	/**
	 * @param text the text to set
	 */
	public void setText(String text) {
		this.text = text;
	}
	/**
	 * @return the image_url
	 */
	public String getImage_url() {
		return image_url;
	}
	/**
	 * @param image_url the image_url to set
	 */
	public void setImage_url(String image_url) {
		this.image_url = image_url;
	}
	/**
	 * @return the color
	 */
	public String getColor() {
		return color;
	}
	/**
	 * @param color the color to set
	 */
	public void setColor(String color) {
		this.color = color;
	}
	/**
	 * @return the footer
	 */
	public String getFooter() {
		return footer;
	}
	/**
	 * @param footer the footer to set
	 */
	public void setFooter(String footer) {
		this.footer = footer;
	}

}
