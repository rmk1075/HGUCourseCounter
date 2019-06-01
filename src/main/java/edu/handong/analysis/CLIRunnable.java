package edu.handong.analysis;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;

public class CLIRunnable {
	
	String input;
	String output;
	String analysis;
	String coursecode;
	String startyear;
	String endyear;
	boolean help;
	
	public void run(String[] args) {
		Options options = createOptions();
		
		if(parseOptions(options, args)){	
			
			//1)help, 2)analysis option is not 1 or 2, 3)the coursecode is not putted when analysis option is 2
			if (help || (!analysis.equals("1") && !analysis.equals("2") || (analysis.equals("2") && coursecode == null))){
				printHelp(options);
				return;
			}
			
			// path is required (necessary) data so no need to have a branch.
			System.out.println("You provided \"" + input + "\" as the value of the option i");
			System.out.println("You provided \"" + output + "\" as the value of the option o");
			System.out.println("You provided \"" + startyear + "\" as the value of the option s");
			System.out.println("You provided \"" + endyear + "\" as the value of the option e");
			
			//i, o, s, e, (c)
			String argument[] = new String[5];
			
			//when analysis option is 2
			if(analysis.contentEquals("2")) {
				argument = new String[5];
				argument[4] = this.coursecode;
				System.out.println("You provided \"" + coursecode + "\" as the value of the option c");
			} else {
				argument[4] = null;
			}
			
			argument[0] = this.input;
			argument[1] = this.output;
			argument[2] = this.startyear;
			argument[3] = this.endyear;
			
			//run main algorithm
			HGUCoursePatternAnalyzer analyzer = new HGUCoursePatternAnalyzer();
			analyzer.run(argument);
		}
	}

	//Parsing Stage
	private boolean parseOptions(Options options, String[] args) {
		CommandLineParser parser = new DefaultParser();

		try {			
			CommandLine cmd = parser.parse(options, args);

			input = cmd.getOptionValue("i");
			output = cmd.getOptionValue("o");
			analysis = cmd.getOptionValue("a");
			coursecode = cmd.getOptionValue("c");
			startyear = cmd.getOptionValue("s");
			endyear = cmd.getOptionValue("e");
			help = cmd.hasOption("h");

		} catch (Exception e) {
			printHelp(options);
			return false;
		}

		return true;
	}

	// Definition Stage
	private Options createOptions() {
		Options options = new Options();

		options.addOption(Option.builder("i").longOpt("input")
				.desc("Set an input file path")
				.hasArg()
				.argName("Input path")
				.required()
				.build());
		
		options.addOption(Option.builder("o").longOpt("output")
				.desc("Set an output file path")
				.hasArg()
				.argName("Output path")
				.required()
				.build());
		
		options.addOption(Option.builder("a").longOpt("analysis")
				.desc("1: Count courses per semester, 2: Count per course name and year")
				.hasArg()
				.argName("Analyses option")
				.required()
				.build());
				
		options.addOption(Option.builder("c").longOpt("coursecode")
				.desc("Course code for '-a 2' option")
				.hasArg()
				.argName("course code")
				.build());
		
		options.addOption(Option.builder("s").longOpt("startyear")
				.desc("Set the start year for analysis e.g., -s 2002")
				.hasArg()
				.argName("Start year for analysis")
				.required()
				.build());
		
		options.addOption(Option.builder("e").longOpt("endyear")
				.desc("Set the end year for analysis e.g., -e 2005")
				.hasArg()
				.argName("End year for analysis")
				.required()
				.build());
		
		options.addOption(Option.builder("h").longOpt("help")
				.argName("Help")
		        .desc("Show a Help page")
		        .build());

		return options;
	}
	
	private void printHelp(Options options) {
		// automatically generate the help statement
		HelpFormatter formatter = new HelpFormatter();
		String header = "HGU Course Analyzer";
		String footer ="";
		formatter.printHelp("HGUCourseCounter", header, options, footer, true);
	}

}
