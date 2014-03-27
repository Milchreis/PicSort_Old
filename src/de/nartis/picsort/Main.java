package de.nartis.picsort;

import org.apache.commons.cli.BasicParser;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.OptionBuilder;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;

import de.nartis.lang.Lang;

public class Main {

	@SuppressWarnings( "static-access" )
	public static void main( String[] args ) {

		CommandLineParser parser = new BasicParser();
		Options options = new Options();

		options.addOption( "h", "help", false, "shows advanced informations" );
		options.addOption( "v", "version", false, "shows the version of this program" );

		options.addOption( OptionBuilder.withArgName( "source" ).hasArg()
				.withDescription( "set the path to the directory of the images" ).create( "s" ) );

		options.addOption( OptionBuilder.withArgName( "destination" ).hasArg()
				.withDescription( "set the path to the directory destionation for the images" )
				.create( "d" ) );

		String src = null;
		String dest = null;
		
		try {
			CommandLine cmd = parser.parse( options, args );

			if( cmd.hasOption( "h" ) ) {
				HelpFormatter formatter = new HelpFormatter();
				formatter.printHelp( "picsort [option] [source] [destination]", options );
			}

			if( cmd.hasOption( "s" ) ) {
				src = cmd.getOptionValue( "s" );
			}
			
			if( cmd.hasOption( "d" ) ) {
				dest = cmd.getOptionValue( "d" );
			}

		} catch( ParseException e1 ) {
			e1.printStackTrace();
		}

		try {
			Lang.setLanguage( "german" );

		} catch( Exception e ) {
			e.printStackTrace();
		}

		new AppController(src, dest);
	}

}
