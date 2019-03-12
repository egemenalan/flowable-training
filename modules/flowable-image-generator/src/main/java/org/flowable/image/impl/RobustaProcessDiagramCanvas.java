package org.flowable.image.impl;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.flowable.bpmn.model.GraphicInfo;
import org.flowable.image.util.ReflectUtil;

public class RobustaProcessDiagramCanvas {

	
    protected static BufferedImage BROWSER_TASK_IMAGE;
    protected static BufferedImage BRWMOUSE_TASK_IMAGE;
    protected static BufferedImage BRWWAIT_TASK_IMAGE;
    protected static BufferedImage BRWSET_TASK_IMAGE;
    protected static BufferedImage BRWGET_TASK_IMAGE;
    protected static BufferedImage BRWCLOSE_TASK_IMAGE;
    protected static BufferedImage BRWSCROLL_TASK_IMAGE;
    protected static BufferedImage BRWALERT_TASK_IMAGE;
    protected static BufferedImage BRWSWITCH_TASK_IMAGE;
    protected static BufferedImage BRWDOWNLOAD_TASK_IMAGE;
    protected static BufferedImage BRWCAPTURE_TASK_IMAGE;
    protected static BufferedImage BRWFUNCTION_TASK_IMAGE;
 
    public void readImages(ClassLoader customClassLoader) {
    	try {
			BROWSER_TASK_IMAGE = ImageIO.read(ReflectUtil.getResource("org/flowable/icons/browserTask.png", customClassLoader));
//          BRWMOUSE_TASK_IMAGE = ImageIO.read(ReflectUtil.getResource("org/flowable/icons/brwMouseTask.png", customClassLoader));
//          BRWWAIT_TASK_IMAGE = ImageIO.read(ReflectUtil.getResource("org/flowable/icons/brwWaitTask.png", customClassLoader));
//          BRWSET_TASK_IMAGE = ImageIO.read(ReflectUtil.getResource("org/flowable/icons/brwSetTask.png", customClassLoader));
//          BRWGET_TASK_IMAGE = ImageIO.read(ReflectUtil.getResource("org/flowable/icons/brwGetTask.png", customClassLoader));
//          BRWCLOSE_TASK_IMAGE = ImageIO.read(ReflectUtil.getResource("org/flowable/icons/brwCloseTask.png", customClassLoader));
//          BRWSCROLL_TASK_IMAGE = ImageIO.read(ReflectUtil.getResource("org/flowable/icons/brwScrollTask.png", customClassLoader));
//          BRWALERT_TASK_IMAGE = ImageIO.read(ReflectUtil.getResource("org/flowable/icons/brwAlertTask.png", customClassLoader));
//          BRWSWITCH_TASK_IMAGE = ImageIO.read(ReflectUtil.getResource("org/flowable/icons/brwSwitchTask.png", customClassLoader));
//          BRWDOWNLOAD_TASK_IMAGE = ImageIO.read(ReflectUtil.getResource("org/flowable/icons/brwDownloadTask.png", customClassLoader));
//          BRWCAPTURE_TASK_IMAGE = ImageIO.read(ReflectUtil.getResource("org/flowable/icons/brwCaptureTask.png", customClassLoader));
//          BRWFUNCTION_TASK_IMAGE = ImageIO.read(ReflectUtil.getResource("org/flowable/icons/brwFunctionTask.png", customClassLoader));

//          EXCELAPP_TASK_IMAGE = ImageIO.read(ReflectUtil.getResource("org/flowable/icons/excelTask.png", customClassLoader));
//          EXCELCLOSE_TASK_IMAGE = ImageIO.read(ReflectUtil.getResource("org/flowable/icons/excelSaveTask.png", customClassLoader));
//          EXCELGET_TASK_IMAGE = ImageIO.read(ReflectUtil.getResource("org/flowable/icons/excelGetTask.png", customClassLoader));
//          EXCELSET_TASK_IMAGE = ImageIO.read(ReflectUtil.getResource("org/flowable/icons/excelSetTask.png", customClassLoader));
//
//          SCRAPEXCEL_TASK_IMAGE = ImageIO.read(ReflectUtil.getResource("org/flowable/icons/excelTask.png", customClassLoader));
//          SCRAPBROWSER_TASK_IMAGE = ImageIO.read(ReflectUtil.getResource("org/flowable/icons/browserTask.png", customClassLoader));
//          SCRAPGET_TASK_IMAGE = ImageIO.read(ReflectUtil.getResource("org/flowable/icons/scrapGetTask.png", customClassLoader));
//          SCRAPEXPORTCSV_TASK_IMAGE = ImageIO.read(ReflectUtil.getResource("org/flowable/icons/scrapExportCsvTask.png", customClassLoader));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}    }

    // her bir stencilset icin asagidaki gibi fonksiyon yazilir
    public BufferedImage getImage(String type) {
    	if (type.equals("customWebApp"))
           return BROWSER_TASK_IMAGE;
    	return null;
    }

}
