/**
 * (c) 2003-2014 MuleSoft, Inc. The software in this package is published under the terms of the CPAL v1.0 license,
 * a copy of which has been included with this distribution in the LICENSE.md file.
 */
package org.mule.modules.mymulelogger;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Serializable;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.apache.log4j.ConsoleAppender;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.MDC;
import org.apache.log4j.PatternLayout;
import org.mule.api.annotations.param.Payload;


public class MyLogger implements Serializable {

	private static final long serialVersionUID = -5259154971175716327L;

	private Logger enterpriseLogger = Logger.getRootLogger();

	private Level serviceLevel = Level.INFO;

	public static final String MY_CONSOLE = "Console";
	public static final String MY_FILE = "File";
	public static final String MY_DATABASE = "Database";
	public static final String LOG_CONFIG_NAME = "common_logger_module.properties";
	public static final String ENV_CONFIGROOT = "env.configRoot";
	public static final String ENV_LOCATION = "env.location";
//	public static final String ENV_DECRYPTION_ALGORITHM = "env.decryptionAlgorithm";
//	public static final String ENV_DECRYPTION_PASSWORD = "env.decryptionPassword";

	// Read values from environment variables, typically from wrapper.conf or
	// command line arguments using -Denv.xxx
	String configRoot = System.getProperty(ENV_CONFIGROOT);
//	String decryptionAlgorithm = System.getProperty(ENV_DECRYPTION_ALGORITHM);
//	String decryptionPassword = System.getProperty(ENV_DECRYPTION_PASSWORD);
String location = (System.getProperty(ENV_LOCATION) == null ? LOG_CONFIG_NAME
			: System.getProperty(ENV_LOCATION));

Properties properties = null;

	// this logger code will be used for mule common logging
	public static String common_logger_code = null;

	/**
	 * This is EnterpriseLogger constructor which takes logger Name, service
	 * name as inputs
	 * 
	 * @param loggerName
	 * @param serviceName
	 */

	public MyLogger(String loggerName, String serviceName) {
		//EnterpriseServiceConfig serviceConfig = null;
		if (null != loggerName) {
			MDC.put(MyLoggerConstants.LOGGER_NAME, loggerName);
		}
		if (null != serviceName) {
			MDC.put(MyLoggerConstants.SERVICE_NAME, serviceName);
		}

		try {
			MDC.put(MyLoggerConstants.NODE_NAME, InetAddress
					.getLocalHost().getHostName());
		} catch (UnknownHostException e) {

			enterpriseLogger
					.error("Error while getting the NodeName in EnterpriseLogger.java Class",
							e);
		}
		
		try {
			File file = new File("common_logger_module.properties");
			FileInputStream fileInput = new FileInputStream(file);
			properties = new Properties();
			properties.load(fileInput);
			fileInput.close();

			Enumeration<?> enuKeys = properties.keys();
			while (enuKeys.hasMoreElements()) {
				String key = (String) enuKeys.nextElement();
				String value = properties.getProperty(key);
				System.out.println(key + ": " + value);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		if (null != properties) {
			Logger.getRootLogger().getLoggerRepository().resetConfiguration();
			String IS_CONSOLE_APPENDER_ENABLE = properties.getProperty("es.common.logger.enable.appender.console");
			String IS_FILE_APPENDER_ENABLE = properties.getProperty("es.common.logger.enable.appender.file");
			//String IS_DATABASE_APPENDER_ENABLE = properties.getProperty("es.common.logger.enable.appender.db");
			String SERVICE_LEVEL = properties.getProperty("es.common.logger.log.level." + serviceName);

			// This default logger code will be used for mule log statements.
			// This can be configured from service config properties.
			common_logger_code = properties.getProperty(MyLoggerConstants.ES_DEFAULT_MULE_LOGGING_CODE);
			if (null != common_logger_code) {
				MDC.put(MyLoggerConstants.PROCESS_STATE,
						common_logger_code);
			}

			if (null != SERVICE_LEVEL) {
				// setting log level for the interface from zuul properties
				serviceLevel = Level.toLevel(properties.getProperty("es.common.logger.log.level." + serviceName));
			}

			// Set log level to specified packages
			// Set All Log level packages
			String allLevelPackages = properties.getProperty(MyLoggerConstants.ALL_LOG_LEVEL_PACKAGES);
			if (null != allLevelPackages) {
				String strKeyArray[] = allLevelPackages.split(",");
				for (int i = 0; i < strKeyArray.length; i++) {
					Logger.getLogger(strKeyArray[i]).setLevel(Level.ALL);
				}
			}

			// Set Trace Log level packages
			String traceLevelPackages = properties.getProperty(MyLoggerConstants.TRACE_LOG_LEVEL_PACKAGES);
			if (null != traceLevelPackages) {
				String strKeyArray[] = traceLevelPackages.split(",");
				for (int i = 0; i < strKeyArray.length; i++) {
					Logger.getLogger(strKeyArray[i]).setLevel(Level.TRACE);
				}
			}

			// Set Debug Log level packages
			String debugLevelPackages = properties.getProperty(MyLoggerConstants.DEBUG_LOG_LEVEL_PACKAGES);
			if (null != debugLevelPackages) {
				String strKeyArray[] = debugLevelPackages.split(",");
				for (int i = 0; i < strKeyArray.length; i++) {
					Logger.getLogger(strKeyArray[i]).setLevel(Level.DEBUG);
				}
			}

			// Set INFO Log level packages
			String infoLevelPackages = properties.getProperty(MyLoggerConstants.INFO_LOG_LEVEL_PACKAGES);
			if (null != infoLevelPackages) {
				String strKeyArray[] = infoLevelPackages.split(",");
				for (int i = 0; i < strKeyArray.length; i++) {
					Logger.getLogger(strKeyArray[i]).setLevel(Level.INFO);
				}
			}

			// Set Warn Log level packages
			String warnLevelPackages = properties.getProperty(MyLoggerConstants.WARN_LOG_LEVEL_PACKAGES);
			if (null != warnLevelPackages) {
				String strKeyArray[] = warnLevelPackages.split(",");
				for (int i = 0; i < strKeyArray.length; i++) {
					Logger.getLogger(strKeyArray[i]).setLevel(Level.WARN);
				}
			}

			// Set Error Log level packages
			String errorLevelPackages = properties.getProperty(MyLoggerConstants.ERROR_LOG_LEVEL_PACKAGES);
			if (null != errorLevelPackages) {
				String strKeyArray[] = errorLevelPackages.split(",");
				for (int i = 0; i < strKeyArray.length; i++) {
					Logger.getLogger(strKeyArray[i]).setLevel(Level.ERROR);
				}
			}

			// Set Fatal Log level packages
			String fatalLevelPackages = properties.getProperty(MyLoggerConstants.FATAL_LOG_LEVEL_PACKAGES);
			if (null != fatalLevelPackages) {
				String strKeyArray[] = fatalLevelPackages.split(",");
				for (int i = 0; i < strKeyArray.length; i++) {
					Logger.getLogger(strKeyArray[i]).setLevel(Level.FATAL);
				}
			}

			// Set Off Log level packages
			String offLevelPackages = properties.getProperty(MyLoggerConstants.OFF_LOG_LEVEL_PACKAGES);
			if (null != offLevelPackages) {
				String strKeyArray[] = offLevelPackages.split(",");
				for (int i = 0; i < strKeyArray.length; i++) {
					Logger.getLogger(strKeyArray[i]).setLevel(Level.OFF);
				}
			}

			if (null != IS_CONSOLE_APPENDER_ENABLE
					&& IS_CONSOLE_APPENDER_ENABLE.equalsIgnoreCase("true")) {

				String setConsoleConversionPattern = properties.getProperty("es.common.logger.enable.appender.console.conversion.pattern");
				if (null != setConsoleConversionPattern) {
					setConsoleAppender(setConsoleConversionPattern,
							serviceLevel);

				}
			}
			if (null != IS_FILE_APPENDER_ENABLE
					&& IS_FILE_APPENDER_ENABLE.equalsIgnoreCase("true")) {
				String setFileConversionPattern = properties.getProperty("es.common.logger.enable.appender.file.conversion.pattern");
				String setFileDatePattern = properties.getProperty("es.common.logger.enable.appender.file.date.pattern");
				String setMaxLogFileSize = properties.getProperty("es.common.logger.enable.appender.file.max.size");
				String maxBackupIndex = properties.getProperty("es.common.logger.enable.appender.file.max.backup");
				// setting the default number of log file backups to 100, this
				// can be configured from zuul otherwise
				int setMaxBackupIndex = 100;
				if (maxBackupIndex.length() > 0) {
					try {
						setMaxBackupIndex = Integer.parseInt(maxBackupIndex);
					} catch (NumberFormatException numFormatException) {
						enterpriseLogger.log(
								Level.toLevel(MyLoggerConstants.ERROR),
								numFormatException.getMessage());
					}
				}
				String fileLocation = properties.getProperty("es.common.logger.enable.appender.file.location")
						+ serviceName + ".log";

				if (null != fileLocation && null != setFileDatePattern) {
					setDailyFileAsAppender(fileLocation,
							setFileConversionPattern, setFileDatePattern,
							setMaxLogFileSize, setMaxBackupIndex, serviceLevel);
				}
			}
			
		}
	}

	/**
	 * @param setConsoleAppenderPattern
	 * @param serviceLevel
	 */
	public void setConsoleAppender(String setConsoleAppenderPattern,
			Level serviceLevel) {
		enterpriseLogger.setLevel(Level.TRACE);
		if (enterpriseLogger.getAppender(MY_CONSOLE) == null) {
			// Creating Pattern Layout
			PatternLayout patternLayout = new PatternLayout(
					setConsoleAppenderPattern);
			ConsoleAppender consoleAppender = new ConsoleAppender(patternLayout);
			consoleAppender.setThreshold(serviceLevel);
			consoleAppender.activateOptions();
			enterpriseLogger.addAppender(consoleAppender);
		}

	}

	/**
	 * @param fileLocation
	 * @param fileConversionPattern
	 * @param fileDatePattern
	 * @param maxLogFileSize
	 * @param maxBackupIndex
	 * @param serviceLevel
	 */
	public void setDailyFileAsAppender(String fileLocation,
			String fileConversionPattern, String fileDatePattern,
			String maxLogFileSize, int maxBackupIndex, Level serviceLevel) {
		enterpriseLogger.setLevel(Level.TRACE);
		if (enterpriseLogger.getAppender(MY_FILE) == null) {
			PatternLayout patternLayout = new PatternLayout(
					fileConversionPattern);
			try {
				TimeSizeRollingFileAppender timeSizeRollingFileAppender = new TimeSizeRollingFileAppender(
						patternLayout, fileLocation, fileDatePattern);
				if (maxLogFileSize.length() > 0)
					timeSizeRollingFileAppender.setMaxFileSize(maxLogFileSize);
				timeSizeRollingFileAppender.setThreshold(serviceLevel);
				timeSizeRollingFileAppender.setMaxBackupIndex(maxBackupIndex);
				timeSizeRollingFileAppender.activateOptions();
				enterpriseLogger.addAppender(timeSizeRollingFileAppender);

			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	
	/**
	 * @param payloadobj
	 * @param processState
	 * @param logMsg
	 */
	public void debug(Object payloadobj, String processState, String logMsg) {
		putMDCParams(payloadobj, processState);
		if (null != logMsg) {
			enterpriseLogger.log(
					Level.toLevel(MyLoggerConstants.DEBUG), logMsg);
		}
		
		// Set default logger code for mule logging statements
		if (null != common_logger_code) {
			MDC.put(MyLoggerConstants.PROCESS_STATE, common_logger_code);
		}
	}

	/**
	 * @param payloadobj
	 * @param headers
	 * @param processState
	 * @param logMsg
	 */
	public void debug(Object payloadobj, Map<String, String> headers,
			String processState, String logMsg) {
		putMDCParams(payloadobj, headers, processState);
		if (null != logMsg) {
			enterpriseLogger.log(
					Level.toLevel(MyLoggerConstants.DEBUG), logMsg);
		}
		
		// Set default logger code for mule logging statements
		if (null != common_logger_code) {
			MDC.put(MyLoggerConstants.PROCESS_STATE, common_logger_code);
		}
	}

	/**
	 * @param payloadobj
	 * @param processState
	 * @param logMsg
	 */
	public void error(Object payloadobj, String processState, String logMsg) {
		putMDCParams(payloadobj, processState);
		if (null != logMsg) {
			enterpriseLogger.log(
					Level.toLevel(MyLoggerConstants.ERROR), logMsg);
		}
		
		// Set default logger code for mule logging statements
		if (null != common_logger_code) {
			MDC.put(MyLoggerConstants.PROCESS_STATE, common_logger_code);
		}
	}

	/**
	 * @param payloadobj
	 * @param headers
	 * @param processState
	 * @param logMsg
	 */
	public void error(Object payloadobj, Map<String, String> headers,
			String processState, String logMsg) {
		putMDCParams(payloadobj, headers, processState);
		if (null != logMsg) {
			enterpriseLogger.log(
					Level.toLevel(MyLoggerConstants.ERROR), logMsg);
		}
		
		// Set default logger code for mule logging statements
		if (null != common_logger_code) {
			MDC.put(MyLoggerConstants.PROCESS_STATE, common_logger_code);
		}
	}

	/**
	 * @param payloadobj
	 * @param processState
	 * @param logMsg
	 * @param error
	 */
	public void error(Object payloadobj, String processState, String logMsg,
			Throwable error) {
		putMDCParams(payloadobj, processState);
		if (null != logMsg && null != error) {
			boolean isErrorThrowable = error instanceof Throwable;
			if (isErrorThrowable) {
				MDC.put(MyLoggerConstants.ERROR_MESSAGE, error);
				enterpriseLogger.log(
						Level.toLevel(MyLoggerConstants.ERROR), logMsg,
						error);
			} else {
				MDC.put(MyLoggerConstants.ERROR_MESSAGE, error);
				enterpriseLogger.log(
						Level.toLevel(MyLoggerConstants.ERROR), logMsg);
			}
		}
		// clear the MDC after logging
		// Remove the current error message from MDC. SO that this error message
		// will not be carry forward from next log statements.'
		MDC.remove(MyLoggerConstants.ERROR_MESSAGE);
		
		// Set default logger code for mule logging statements
		if (null != common_logger_code) {
			MDC.put(MyLoggerConstants.PROCESS_STATE, common_logger_code);
		}

	}

	/**
	 * @param payloadobj
	 * @param headers
	 * @param processState
	 * @param logMsg
	 * @param error
	 */
	public void error(Object payloadobj, Map<String, String> headers,
			String processState, String logMsg, Throwable error) {
		putMDCParams(payloadobj, headers, processState);
		if (null != logMsg && null != error) {
			boolean isErrorThrowable = error instanceof Throwable;
			if (isErrorThrowable) {
				MDC.put(MyLoggerConstants.ERROR_MESSAGE, error);
				enterpriseLogger.log(
						Level.toLevel(MyLoggerConstants.ERROR), logMsg,
						error);
			} else {
				MDC.put(MyLoggerConstants.ERROR_MESSAGE, error);
				enterpriseLogger.log(
						Level.toLevel(MyLoggerConstants.ERROR), logMsg);
			}
		}
		// to clear the MDC after logging
		// Remove the current error message from MDC. SO that this error message
		// will not be carry forward from next log statements.
		MDC.remove(MyLoggerConstants.ERROR_MESSAGE);
		
		// Set default logger code for mule logging statements
		if (null != common_logger_code) {
			MDC.put(MyLoggerConstants.PROCESS_STATE, common_logger_code);
		}
	}

	/**
	 * @param payloadobj
	 * @param processState
	 * @param logMsg
	 */
	public void fatal(Object payloadobj, String processState, String logMsg) {
		putMDCParams(payloadobj, processState);
		if (null != logMsg) {
			enterpriseLogger.log(
					Level.toLevel(MyLoggerConstants.FATAL), logMsg);
		}
		
		// Set default logger code for mule logging statements
		if (null != common_logger_code) {
			MDC.put(MyLoggerConstants.PROCESS_STATE, common_logger_code);
		}
	}

	/**
	 * @param payloadobj
	 * @param headers
	 * @param processState
	 * @param logMsg
	 */
	public void fatal(Object payloadobj, Map<String, String> headers,
			String processState, String logMsg) {
		putMDCParams(payloadobj, headers, processState);
		if (null != logMsg) {
			enterpriseLogger.log(
					Level.toLevel(MyLoggerConstants.FATAL), logMsg);
		}
		
		// Set default logger code for mule logging statements
		if (null != common_logger_code) {
			MDC.put(MyLoggerConstants.PROCESS_STATE, common_logger_code);
		}
	}

	/**
	 * @param payloadobj
	 * @param processState
	 * @param logMsg
	 * @param error
	 */
	public void fatal(Object payloadobj, String processState, String logMsg,
			Throwable error) {
		putMDCParams(payloadobj, processState);
		if (null != logMsg && null != error) {
			boolean isErrorThrowable = error instanceof Throwable;
			if (isErrorThrowable) {
				MDC.put(MyLoggerConstants.ERROR_MESSAGE, error);
				enterpriseLogger.log(
						Level.toLevel(MyLoggerConstants.FATAL), logMsg,
						error);
			} else {
				MDC.put(MyLoggerConstants.ERROR_MESSAGE, error);
				enterpriseLogger.log(
						Level.toLevel(MyLoggerConstants.FATAL), logMsg);
			}
		}
		// to clear the MDC after logging
		// Remove the current error message from MDC. SO that this error message
		// will not be carry forward from next log statements.
		MDC.remove(MyLoggerConstants.ERROR_MESSAGE);
		
		// Set default logger code for mule logging statements
		if (null != common_logger_code) {
			MDC.put(MyLoggerConstants.PROCESS_STATE, common_logger_code);
		}
	}

	/**
	 * @param payloadobj
	 * @param headers
	 * @param processState
	 * @param logMsg
	 * @param error
	 */
	public void fatal(Object payloadobj, Map<String, String> headers,
			String processState, String logMsg, Throwable error) {
		putMDCParams(payloadobj, headers, processState);
		if (null != logMsg && null != error) {
			boolean isErrorThrowable = error instanceof Throwable;
			if (isErrorThrowable) {
				MDC.put(MyLoggerConstants.ERROR_MESSAGE, error);
				enterpriseLogger.log(
						Level.toLevel(MyLoggerConstants.FATAL), logMsg,
						error);
			} else {
				MDC.put(MyLoggerConstants.ERROR_MESSAGE, error);
				enterpriseLogger.log(
						Level.toLevel(MyLoggerConstants.FATAL), logMsg);
			}
		}
		// to clear the MDC after logging
		// Remove the current error message from MDC. SO that this error message
		// will not be carry forward from next log statements.
		MDC.remove(MyLoggerConstants.ERROR_MESSAGE);
		
		// Set default logger code for mule logging statements
		if (null != common_logger_code) {
			MDC.put(MyLoggerConstants.PROCESS_STATE, common_logger_code);
		}
	}

	/**
	 * @param payloadobj
	 * @param processState
	 * @param logMsg
	 */
	public void info(Object payloadobj, String processState, String logMsg) {
		putMDCParams(payloadobj, processState);
		if (null != logMsg) {
			enterpriseLogger.log(Level.toLevel(MyLoggerConstants.INFO),
					logMsg);
		}
		
		// Set default logger code for mule logging statements
		if (null != common_logger_code) {
			MDC.put(MyLoggerConstants.PROCESS_STATE, common_logger_code);
		}
	}

	/**
	 * @param payloadobj
	 * @param headers
	 * @param processState
	 * @param logMsg
	 */
	public void info(Object payloadobj, Map<String, String> headers,
			String processState, String logMsg) {
		putMDCParams(payloadobj, headers, processState);
		if (null != logMsg) {
			enterpriseLogger.log(Level.toLevel(MyLoggerConstants.INFO),
					logMsg);
		}
		
		// Set default logger code for mule logging statements
		if (null != common_logger_code) {
			MDC.put(MyLoggerConstants.PROCESS_STATE, common_logger_code);
		}
	}

	/**
	 * @param payloadobj
	 * @param processState
	 * @param logMsg
	 */
	public void trace(Object payloadobj, String processState, String logMsg) {
		putMDCParams(payloadobj, processState);
		if (null != logMsg) {
			enterpriseLogger.log(
					Level.toLevel(MyLoggerConstants.TRACE), logMsg);
		}
		
		// Set default logger code for mule logging statements
		if (null != common_logger_code) {
			MDC.put(MyLoggerConstants.PROCESS_STATE, common_logger_code);
		}
	}

	/**
	 * @param payloadobj
	 * @param headers
	 * @param processState
	 * @param logMsg
	 */
	public void trace(Object payloadobj, Map<String, String> headers,
			String processState, String logMsg) {
		putMDCParams(payloadobj, headers, processState);
		if (null != logMsg) {
			enterpriseLogger.log(
					Level.toLevel(MyLoggerConstants.TRACE), logMsg);
		}
		
		// Set default logger code for mule logging statements
		if (null != common_logger_code) {
			MDC.put(MyLoggerConstants.PROCESS_STATE, common_logger_code);
		}
	}

	/**
	 * @param payloadobj
	 * @param processState
	 * @param logMsg
	 */
	public void warn(Object payloadobj, String processState, String logMsg) {
		putMDCParams(payloadobj, processState);
		if (null != logMsg) {
			enterpriseLogger.log(Level.toLevel(MyLoggerConstants.WARN),
					logMsg);
		}
		
		// Set default logger code for mule logging statements
		if (null != common_logger_code) {
			MDC.put(MyLoggerConstants.PROCESS_STATE, common_logger_code);
		}
	}

	/**
	 * @param payloadobj
	 * @param headers
	 * @param processState
	 * @param logMsg
	 */
	public void warn(Object payloadobj, Map<String, String> headers,
			String processState, String logMsg) {
		putMDCParams(payloadobj, headers, processState);
		if (null != logMsg) {
			enterpriseLogger.log(Level.toLevel(MyLoggerConstants.WARN),
					logMsg);
		}
		
		// Set default logger code for mule logging statements
		if (null != common_logger_code) {
			MDC.put(MyLoggerConstants.PROCESS_STATE, common_logger_code);
		}
	}

	/**
	 * @param payloadobj
	 * @param processState
	 * @param logMsg
	 * @param error
	 */
	public void warn(Object payloadobj, String processState, String logMsg,
			Throwable error) {
		putMDCParams(payloadobj, processState);

		if (null != logMsg && null != error) {
			boolean isErrorThrowable = error instanceof Throwable;
			if (isErrorThrowable) {
				MDC.put(MyLoggerConstants.ERROR_MESSAGE, error);
				enterpriseLogger.log(
						Level.toLevel(MyLoggerConstants.WARN), logMsg,
						error);
			} else {
				MDC.put(MyLoggerConstants.ERROR_MESSAGE, error);
				enterpriseLogger.log(
						Level.toLevel(MyLoggerConstants.WARN), logMsg);
			}
		}
		// to clear the MDC after logging
		// Remove the current error message from MDC. SO that this error message
		// will not be carry forward from next log statements.
		MDC.remove(MyLoggerConstants.ERROR_MESSAGE);
		
		// Set default logger code for mule logging statements
		if (null != common_logger_code) {
			MDC.put(MyLoggerConstants.PROCESS_STATE, common_logger_code);
		}
	}

	/**
	 * @param payloadobj
	 * @param headers
	 * @param processState
	 * @param logMsg
	 * @param error
	 */
	public void warn(Object payloadobj, Map<String, String> headers,
			String processState, String logMsg, Throwable error) {
		putMDCParams(payloadobj, headers, processState);

		if (null != logMsg && null != error) {
			boolean isErrorThrowable = error instanceof Throwable;
			if (isErrorThrowable) {
				MDC.put(MyLoggerConstants.ERROR_MESSAGE, error);
				enterpriseLogger.log(
						Level.toLevel(MyLoggerConstants.WARN), logMsg,
						error);
			} else {
				MDC.put(MyLoggerConstants.ERROR_MESSAGE, error);
				enterpriseLogger.log(
						Level.toLevel(MyLoggerConstants.WARN), logMsg);
			}
		}
		// to clear the MDC after logging
		// Remove the current error message from MDC. SO that this error message
		// will not be carry forward from next log statements.
		MDC.remove(MyLoggerConstants.ERROR_MESSAGE);
		
		// Set default logger code for mule logging statements
		if (null != common_logger_code) {
			MDC.put(MyLoggerConstants.PROCESS_STATE, common_logger_code);
		}
	}

	/**
	 * @param payloadobj
	 * @param processState
	 * @param logMsg
	 */
	public void all(Object payloadobj, String processState, String logMsg) {
		putMDCParams(payloadobj, processState);
		if (null != logMsg) {
			enterpriseLogger.log(Level.toLevel(MyLoggerConstants.ALL),
					logMsg);
		}
		
		// Set default logger code for mule logging statements
		if (null != common_logger_code) {
			MDC.put(MyLoggerConstants.PROCESS_STATE, common_logger_code);
		}
	}

	/**
	 * @param payloadobj
	 * @param headers
	 * @param processState
	 * @param logMsg
	 */
	public void all(Object payloadobj, Map<String, String> headers,
			String processState, String logMsg) {
		putMDCParams(payloadobj, headers, processState);
		if (null != logMsg) {
			enterpriseLogger.log(Level.toLevel(MyLoggerConstants.ALL),
					logMsg);
		}
		
		// Set default logger code for mule logging statements
		if (null != common_logger_code) {
			MDC.put(MyLoggerConstants.PROCESS_STATE, common_logger_code);
		}
	}

	/**
	 * @param payloadobj
	 * @param processState
	 * @param logMsg
	 * @param error
	 */
	public void all(Object payloadobj, String processState, String logMsg,
			Throwable error) {
		putMDCParams(payloadobj, processState);

		if (null != logMsg && null != error) {
			boolean isErrorThrowable = error instanceof Throwable;
			if (isErrorThrowable) {
				MDC.put(MyLoggerConstants.ERROR_MESSAGE, error);
				enterpriseLogger.log(
						Level.toLevel(MyLoggerConstants.ALL), logMsg,
						error);
			} else {
				MDC.put(MyLoggerConstants.ERROR_MESSAGE, error);
				enterpriseLogger.log(
						Level.toLevel(MyLoggerConstants.ALL), logMsg);
			}
		}
		// to clear the MDC after logging
		// Remove the current error message from MDC. SO that this error message
		// will not be carry forward from next log statements.
		MDC.remove(MyLoggerConstants.ERROR_MESSAGE);
		
		// Set default logger code for mule logging statements
		if (null != common_logger_code) {
			MDC.put(MyLoggerConstants.PROCESS_STATE, common_logger_code);
		}
	}

	/**
	 * @param payloadobj
	 * @param headers
	 * @param processState
	 * @param logMsg
	 * @param error
	 */
	public void all(Object payloadobj, Map<String, String> headers,
			String processState, String logMsg, Throwable error) {
		putMDCParams(payloadobj, headers, processState);

		if (null != logMsg && null != error) {
			boolean isErrorThrowable = error instanceof Throwable;
			if (isErrorThrowable) {
				MDC.put(MyLoggerConstants.ERROR_MESSAGE, error);
				enterpriseLogger.log(
						Level.toLevel(MyLoggerConstants.ALL), logMsg,
						error);
			} else {
				MDC.put(MyLoggerConstants.ERROR_MESSAGE, error);
				enterpriseLogger.log(
						Level.toLevel(MyLoggerConstants.ALL), logMsg);
			}
		}
		// to clear the MDC after logging
		// Remove the current error message from MDC. SO that this error message
		// will not be carry forward from next log statements.
		MDC.remove(MyLoggerConstants.ERROR_MESSAGE);
		
		// Set default logger code for mule logging statements
		if (null != common_logger_code) {
			MDC.put(MyLoggerConstants.PROCESS_STATE, common_logger_code);
		}
	}

	/**
	 * To turn off logging
	 */
	public void off() {
		enterpriseLogger.setLevel(Level.OFF);
	}

	/**
	 * This putMDCParams method add the custome Logger Keys in MDC params to
	 * populate in Log files and databse when the custom key used in the
	 * configuration.
	 * 
	 * @param emObject
	 * @param processState
	 */
	public void putMDCParams(@Payload Object emObject, String processState) {
		Map<String, String> payloadMDCParamMap = new HashMap<String, String>();
		payloadMDCParamMap.put(MyLoggerConstants.MESSAGE_ID,"MESSAGE_ID");
		payloadMDCParamMap.put(MyLoggerConstants.ACTION,"ACTION");
		payloadMDCParamMap.put(MyLoggerConstants.SOURCE,"SOURCE");
		payloadMDCParamMap.put(MyLoggerConstants.RESOURCE_ID,"RESOURCE_ID");
		payloadMDCParamMap.put(MyLoggerConstants.RESOURCE_NAME,"RESOURCE_NAME");

		if (null != payloadMDCParamMap
				&& payloadMDCParamMap.keySet().size() > 0) {
			Iterator<Entry<String, String>> entries = payloadMDCParamMap
					.entrySet().iterator();
			while (entries.hasNext()) {
				Entry<String, String> entry = entries
						.next();
				MDC.put(entry.getKey(), entry.getValue());
			}
		}
		if (null != processState)
			MDC.put(MyLoggerConstants.PROCESS_STATE, processState);

	}

	/**
	 * This putMDCParams method add the custom Logger Keys in MDC params to
	 * populate in Log files and database when the custom key used in the
	 * configuration.
	 * 
	 * @param emObject
	 * @param headers
	 * @param processState
	 */
	public void putMDCParams(@Payload Object emObject,
			Map<String, String> headers, String processState) {
		Map<String, String> payloadMDCParamMap = new HashMap<String, String>();
		
				if (null != headers.get("messageId")) {
					payloadMDCParamMap.put(
							MyLoggerConstants.MESSAGE_ID,
							headers.get("messageId"));
				}
				if (null != headers.get("messageSource")) {
					payloadMDCParamMap.put(MyLoggerConstants.SOURCE,
							headers.get("messageSource"));
				}
				if (null != headers.get("messageAction")) {
					payloadMDCParamMap.put(MyLoggerConstants.ACTION,
							headers.get("messageAction"));
				}
				if (null != headers.get("resourceId")) {
					payloadMDCParamMap.put(
							MyLoggerConstants.RESOURCE_ID,
							headers.get("resourceId"));
				}
				if (null != headers.get("resourceName")) {
					payloadMDCParamMap.put(
							MyLoggerConstants.RESOURCE_NAME,
							headers.get("resourceName"));
				}
				if (null != headers.get("sourceId")) {
					payloadMDCParamMap.put(MyLoggerConstants.SOURCE_ID,
							headers.get("sourceId"));
				}

			
				if (null != payloadMDCParamMap
				&& payloadMDCParamMap.keySet().size() > 0) {
			Iterator<Entry<String, String>> entries = payloadMDCParamMap
					.entrySet().iterator();
			while (entries.hasNext()) {
				Entry<String, String> entry = entries
						.next();
				MDC.put(entry.getKey(), entry.getValue());
			}
		}
		if (null != processState) {
			MDC.put(MyLoggerConstants.PROCESS_STATE, processState);
		}

	}

	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this);
	}

	public String getConfigRoot() {
		return configRoot;
	}

	public void setConfigRoot(String configRoot) {
		this.configRoot = configRoot;
	}



	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

}
