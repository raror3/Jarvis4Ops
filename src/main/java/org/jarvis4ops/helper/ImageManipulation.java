/**
 * 
 */
package org.jarvis4ops.helper;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.font.FontRenderContext;
import java.awt.font.TextLayout;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;

import javax.imageio.ImageIO;

import org.apache.commons.codec.binary.Base64;
import org.jarvis4ops.controller.JiraController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author raror3
 */
@Component
public class ImageManipulation {
	private static final Logger log = LoggerFactory.getLogger(JiraController.class);

	public String manipulateImage(String inputString) {

		String outputFilePath = "src/main/resources/emptyWrittenImage.jpg";
		BufferedImage image;
		try {
			image = ImageIO.read(new File("src/main/resources/emptyPage.jpg"));

		    Graphics g2 = image.getGraphics();
		    Graphics2D g = (Graphics2D) g2;
		    FontRenderContext frc = g.getFontRenderContext();
		    Font font = new Font("TimesNewRoman", Font.PLAIN, 14);
		    TextLayout layout = new TextLayout(inputString.toString(), font, frc);
		    String[] outputs = inputString.toString().split("\n");
		    int counter = 0;
		    for(int i=0; i<outputs.length; i++){
		        if (i==1) {
		        	g2.setColor(Color.BLUE);
			        g.setFont(new Font("TimesNewRoman", Font.PLAIN, 14));
		        } else {
			        g2.setColor(Color.BLACK);
			        g.setFont(new Font("TimesNewRoman", Font.PLAIN, 14));
		        }
		        g.drawString(outputs[i], 50,(int) (20+(i*layout.getBounds().getHeight()*1.1)+1));
		        counter++;
		    }
		    System.out.println("counter" + counter);

		    g.dispose();

		    ImageIO.write(image, "jpg", new File(outputFilePath));

		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return outputFilePath;
	}

	/**
	 * Encodes the byte array into base64 string
	 * @param imageByteArray - byte array
	 * @return String a {@link java.lang.String}
	 */
	public static String encodeImage(byte[] imageByteArray){		
		return Base64.encodeBase64URLSafeString(imageByteArray);		
	}
	
	/**
	 * Decodes the base64 string into byte array
	 * @param imageDataString - a {@link java.lang.String} 
	 * @return byte array
	 */
	public static byte[] decodeImage(String imageDataString) {		
		return Base64.decodeBase64(imageDataString);
	}
}
