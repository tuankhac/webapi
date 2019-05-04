package com.neo.api.utils;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class ImageResizer {

	/**
	 * Resizes an image to a absolute width and height (the image may not be
	 * proportional)
	 * 
	 * @param inputImagePath
	 *            Path of the original image
	 * @param outputImagePath
	 *            Path to save the resized image
	 * @param scaledWidth
	 *            absolute width in pixels
	 * @param scaledHeight
	 *            absolute height in pixels
	 * @throws IOException
	 */
	public static void resize(String inputImagePath, String outputImagePath, int scaledWidth, int scaledHeight)
			throws IOException {
		// reads input image
		File inputFile = new File(inputImagePath);
		BufferedImage inputImage = ImageIO.read(inputFile);

		// creates output image
		BufferedImage outputImage = new BufferedImage(scaledWidth, scaledHeight, inputImage.getType());

		// scales the input image to the output image
		Graphics2D g2d = outputImage.createGraphics();
		g2d.drawImage(inputImage, 0, 0, scaledWidth, scaledHeight, null);
		g2d.dispose();

		// extracts extension of output file
		String formatName = outputImagePath.substring(outputImagePath.lastIndexOf(".") + 1);

		// writes to output file
		ImageIO.write(outputImage, formatName, new File(outputImagePath));
	}

	/**
	 * Resizes an image by a percentage of original size (proportional).
	 * 
	 * @param inputImagePath
	 *            Path of the original image
	 * @param outputImagePath
	 *            Path to save the resized image
	 * @param percent
	 *            a double number specifies percentage of the output image over the
	 *            input image.
	 * @throws IOException
	 */
	public static void resize(String inputImagePath, String outputImagePath, double percent) throws IOException {
		File inputFile = new File(inputImagePath);
		BufferedImage inputImage = ImageIO.read(inputFile);
		int scaledWidth = (int) (inputImage.getWidth() * percent);
		int scaledHeight = (int) (inputImage.getHeight() * percent);
		resize(inputImagePath, outputImagePath, scaledWidth, scaledHeight);
	}

	public static byte[] scale(byte[] fileData, int width, int height) {
		ByteArrayInputStream in = new ByteArrayInputStream(fileData);
		ByteArrayOutputStream buffer = null;
		try {
			BufferedImage img = ImageIO.read(in);
			if (height == 0) {
				height = (width * img.getHeight()) / img.getWidth();
			}
			if (width == 0) {
				width = (height * img.getWidth()) / img.getHeight();
			}
			Image scaledImage = img.getScaledInstance(width, height, Image.SCALE_SMOOTH);
			BufferedImage imageBuff = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
			imageBuff.getGraphics().drawImage(scaledImage, 0, 0, new Color(0, 0, 0), null);
			buffer = new ByteArrayOutputStream();
			ImageIO.write(imageBuff, "jpg", buffer);

		} catch (IOException e) {
			try {
				throw new Exception("IOException in scale");
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}
		return buffer.toByteArray();
	}
}
