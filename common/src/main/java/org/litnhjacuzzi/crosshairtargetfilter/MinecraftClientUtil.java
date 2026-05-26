package org.litnhjacuzzi.crosshairtargetfilter;

import java.io.InputStream;
import java.io.InputStreamReader;

import net.minecraft.DetectedVersion;
import net.minecraft.util.GsonHelper;

public class MinecraftClientUtil {
	
	private static final int currentProtocolVersion;
	
	public static boolean isGameVersionReached(int protocolVersion) {
		return currentProtocolVersion >= protocolVersion;
	}
	
	static {
		int protocolVersion;
		try (InputStream is = DetectedVersion.class.getResourceAsStream("/version.json");
				InputStreamReader isr = new InputStreamReader(is)) {
			protocolVersion = GsonHelper.getAsInt(GsonHelper.parse(isr), "protocol_version");
		} catch (Exception e) {
			CTFCore.LOGGER.warn("[CrosshairTarget Filter] Failed to get protocol version!");
			protocolVersion = Integer.MAX_VALUE;
		}
		currentProtocolVersion = protocolVersion;
	}
}
