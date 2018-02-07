import java.util.*;
import java.io.*;

public class TraceRouteIP {

	public String trace_ip(String ip) {

		String network_ip = ip;

		String command = "traceroute " + network_ip;

		String route = executeCommand(command);

		try (BufferedWriter bw = new BufferedWriter(new FileWriter("/home/codebuster/ProjectLab_Trial/TraceRoute/TraceIP.txt"))) {

			bw.write(route + "\n");
			bw.newLine();
			bw.flush();

		} catch (IOException e) {

			e.printStackTrace();
		}

		return route;
	}

	public String executeCommand(String command) {

		StringBuffer route = new StringBuffer();

		Process p;

		try {

			p = Runtime.getRuntime().exec(command);
			p.waitFor();

			BufferedReader reader = new BufferedReader(new InputStreamReader(p.getInputStream()));

			String line = "";

			while ((line = reader.readLine())!=null) {

				route.append(line + "\n");
			}
		
		} catch (Exception e) {

			e.printStackTrace();
		}

		return route.toString();

	}
}