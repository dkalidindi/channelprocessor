package com.slingmedia.channel.accessor.service;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.codehaus.jackson.JsonEncoding;
import org.codehaus.jackson.JsonFactory;
import org.codehaus.jackson.JsonGenerator;
import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.JsonToken;
import org.codehaus.jackson.map.MappingJsonFactory;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.annotate.JsonSerialize;

import com.slingmedia.channel.exception.ChannelException;
import com.slingmedia.channel.model.Channel;
import com.slingmedia.channel.model.MediaDevice;

/**
 *  <p>Usecase:
 *  <p>
 *  A web service to get the list of channels available on a set-top box. The implementation of this web service is as follows,
 *      <li> make a remote call to get the list of ordered channels available on a set-top box in JSON format
 *      <li> insert an attribute "channel_number" to each element in the collection and set with the value of "channel" attribute
 *      <li> return the channels list by preserving the order
 *
 * <p> Approach:
 *  <li> Using Jackson for JSON parsing. If Jackson is not required this could be replaced by 
 *      hand-written parser using StringTokenizer and other String utilities.
 *  <li> Using Jackson treaming API as Pull parser to effectively nullify memory foot print.
 *      Pull parsers iterate over input source with memory foot print of current curser.
 *  <li> Writing output as json to channels_output.json 
 *  <p>
 *  Alternate approach for parsing:
 *  1. Current parsing adopted is bit of hard-coded json structure. As JSON given is not exactly in object - array format i used this.
 * <p>
 * @author Dileep Kalidindi.
 *
 */
public class ChannelService {

	public static void main(String[] args) {

		MediaDevice md = new MediaDevice();
		List<Channel> channelList = new ArrayList<Channel>();
		String jsonInputFile = "/channels.json";
		String jsonOutputFile = "channels_output.json";

		try {
			parseChannelJSON(md, channelList, jsonInputFile);
			writeChannelJSON(md, channelList, jsonOutputFile);
			
		} catch (ChannelException e) {
			// Logging statement
			System.out.print(e.getLocalizedMessage());
		}
	}

	private static void writeChannelJSON(MediaDevice md,
			List<Channel> channelList, String jsonOutputFile) {
		 
		ObjectMapper mapper = new ObjectMapper();
		mapper.setSerializationInclusion(JsonSerialize.Inclusion.NON_NULL);
		  
		
		 JsonFactory jF = new MappingJsonFactory(mapper);
		 

         try {
			JsonGenerator generator = jF.createJsonGenerator(new File(jsonOutputFile), JsonEncoding.UTF8);

			generator.writeStartObject(); 

			generator.writeFieldName("channels");
			
			generator.writeStartObject();
			
			generator.writeFieldName(md.getDeviceId());
			
			generator.writeStartObject();
			for (Channel channel : channelList) {

				if (channel != null) {
						generator.writeObjectField(String.valueOf(channel.getUsid()), channel);
									
				}
			}
			generator.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	private static void parseChannelJSON(MediaDevice md,
			List<Channel> channelList, String jsonInputFile)
			throws ChannelException {

		JsonFactory jasonFactory = new MappingJsonFactory();
		JsonParser parser = null;
		try {
			
			// Equivalent code for WebSerivce Remote procedure call
			parser = jasonFactory.createJsonParser(ChannelService.class
					.getClass().getResourceAsStream(jsonInputFile));
			while (parser.nextToken() != null) {

				String current = parser.getCurrentName();
				if (current != null) {
					if (current.equals("channels")) {
						parser.nextToken();
					} else if (current == JsonToken.START_OBJECT.toString()) {
						parser.nextToken();
						continue;
					} else if (current.startsWith("dish")) {
						md.setDeviceId(current);
						parser.nextToken();
						continue;
					} else if (isNumeric(current)) {
						parser.nextToken();
						Channel currentChannel = parser
								.readValueAs(Channel.class);
						if (currentChannel != null) {
							currentChannel.setChannel_number(currentChannel
									.getChannel());
							channelList.add(currentChannel);
						}
					}
				} else {
					parser.nextToken();
				}
			}
		} catch (IOException e) {
			throw new ChannelException();
		} finally {
			try {
				parser.close();
			} catch (IOException e) {
				throw new ChannelException();
			}
		}

		md.setChannels(channelList);
	}

	/**
	 * To check if the id passed a string is numeric
	 * <p>
	 * 
	 * @param str
	 * @return boolean status
	 */
	private static boolean isNumeric(String str) {
		if (str == null) {
			return false;
		}
		int sz = str.length();
		for (int i = 0; i < sz; i++) {
			if (!Character.isDigit(str.charAt(i))) {
				return false;
			}
		}
		return true;
	}
}