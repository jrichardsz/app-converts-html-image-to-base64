package org.jrichardsz.test.base64;

import org.jrichardsz.apps.htmlimagetobase64.common.util.ImageUtil;
import org.junit.Test;

import com.linet.util.file.FileUtil;

public class TestBase64{

//	
//    System.out.println(imgstr);
////  newImg = decodeToImage(imgstr);
////  ImageIO.write(newImg, "png", new File("files/img/CopyOfTestImage.png"));
//  /* Test image to string and string to image finish */
	
	@Test
	public void testEncodeToString() throws Exception{
		
		String imagePath = FileUtil.getPathFromWhereApplicationIsRunning()+"\\src\\test\\resources\\img-to-load-000.jpg";
		String imgstr;
        imgstr =ImageUtil.encodeToString(imagePath, "jpg");
        System.out.println(imgstr);
	}

}
