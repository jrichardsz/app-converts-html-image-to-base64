package org.jrichardsz.apps.htmlimagetobase64.common.util;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

@SuppressWarnings("restriction")
public class ImageUtil{

	/**
	 * Decode string to image
	 * 
	 * @param imageString
	 *            The string to decode
	 * @return decoded image
	 */
	public static BufferedImage decodeToImage(String imageString){

		BufferedImage image=null;
		byte[] imageByte;
		try{
			BASE64Decoder decoder=new BASE64Decoder();
			imageByte=decoder.decodeBuffer(imageString);
			ByteArrayInputStream bis=new ByteArrayInputStream(imageByte);
			image=ImageIO.read(bis);
			bis.close();
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return image;
	}

	/**
	 * Encode image to string
	 * 
	 * @param image
	 *            The image to encode
	 * @param formatName jpeg, bmp, ...
	 * @return encoded string
	 * @throws Exception
	 */

	public static String encodeToString(BufferedImage image,String formatName) throws Exception{

		String imageString=null;
		ByteArrayOutputStream bos=new ByteArrayOutputStream();

		try{
			ImageIO.write(image,formatName,bos);
			byte[] imageBytes=bos.toByteArray();

			BASE64Encoder encoder=new BASE64Encoder();
			imageString=encoder.encode(imageBytes);

			bos.close();

		}
		catch(IOException e){
			throw new Exception("Error when try to convert image to bse64.",e);
		}

		return imageString;
	}

	public static String encodeToString(String pathImage,String formatName) throws Exception{

		BufferedImage image=null;
		try{
			image=ImageIO.read(new File(pathImage));
		}
		catch(IOException e1){
			throw new Exception("Error when try to read image " + pathImage,e1);
		}

		return encodeToString(image,formatName);

	}
}
