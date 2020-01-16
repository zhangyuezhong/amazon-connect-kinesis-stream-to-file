package com.telstra.amazonconnect_kinesis_demo;

import java.io.File;
import java.nio.file.Path;
import java.util.concurrent.CompletableFuture;

import com.amazonaws.kvs.streaming.connect.AudioUtils;
import com.amazonaws.kvs.streaming.connect.KVSAmazonConnectStreaming;
import com.amazonaws.kvs.streaming.connect.KVSUtils;
import com.amazonaws.regions.Regions;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * Unit test for simple App.
 */
public class AppTest extends TestCase {
	/**
	 * Create the test case
	 *
	 * @param testName name of the test case
	 */
	public AppTest(String testName) {
		super(testName);
	}

	/**
	 * @return the suite of tests being tested
	 */
	public static Test suite() {
		return new TestSuite(AppTest.class);
	}

	/**
	 * Rigourous Test :-)
	 */
	public void testApp() {

		String streamARN = "arn:aws:kinesisvideo:ap-southeast-2:171147132640:stream/call-streams-connect-bts-collaboration-contact-e847dd57-d452-4123-a073-cc55c91f5e68/1579065140491";
		String startFragmentNumber = "91343852333181432392682062625804787330172400189";
		String contactId = "ff078505-6a54-4310-8b3b-c2c3afe7fa0e";

		KVSAmazonConnectStreaming kacs = new KVSAmazonConnectStreaming();

		try {
			CompletableFuture<Path> completableFuture = kacs.getStartStreamingFuture(Regions.AP_SOUTHEAST_2, streamARN,
					startFragmentNumber, contactId, KVSUtils.TrackName.AUDIO_FROM_CUSTOMER);
			Path rawFile = completableFuture.get();
			File wavFile = AudioUtils.convertRawToWav(rawFile.toString());
			System.out.println("wavFile" + wavFile.getAbsolutePath());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
