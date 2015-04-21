/**
 * (c) 2003-2014 MuleSoft, Inc. The software in this package is published under the terms of the CPAL v1.0 license,
 * a copy of which has been included with this distribution in the LICENSE.md file.
 */
package org.mule.modules.mymulelogger;

public class MyLoggerConstants {
	/**
	 * Logger Levels
	 */

	public static final String INFO = "INFO";
	public static final String DEBUG = "DEBUG";
	public static final String WARN = "WARN";
	public static final String ERROR = "ERROR";
	public static final String TRACE = "TRACE";
	public static final String FATAL = "FATAL";
	public static final String ALL = "ALL";
	public static final String OFF = "OFF";

	/**
	 * MDC Parameters
	 */

	public static final String PROCESS_STATE = "PROCESS_STATE";
	public static final String LOGGER_NAME = "LOGGER_NAME";
	public static final String SERVICE_NAME = "SERVICE_NAME";
	public static final String NODE_NAME = "NODE_NAME";
	public static final String FILE_LOCATION = "FILE_LOCATION";
	public static final String ERROR_MESSAGE = "ERROR_MESSAGE";

	/**
	 * Enterprise Message Header MDC Parameters
	 */

	public static final String MESSAGE_ID = "MESSAGE_ID";
	public static final String SOURCE = "SOURCE";
	public static final String ACTION = "ACTION";
	public static final String RESOURCE_NAME = "RESOURCE_NAME";
	public static final String RESOURCE_ID = "RESOURCE_ID";
	public static final String SOURCE_ID = "SOURCE_ID";

	public static final String ALL_LOG_LEVEL_PACKAGES = "common.logger.global.packaglevel.ALL";
	public static final String TRACE_LOG_LEVEL_PACKAGES = "common.logger.global.packaglevel.TRACE";
	public static final String DEBUG_LOG_LEVEL_PACKAGES = "common.logger.global.packaglevel.DEBUG";
	public static final String INFO_LOG_LEVEL_PACKAGES = "common.logger.global.packaglevel.INFO";
	public static final String WARN_LOG_LEVEL_PACKAGES = "common.logger.global.packaglevel.WARN";
	public static final String ERROR_LOG_LEVEL_PACKAGES = "common.logger.global.packaglevel.ERROR";
	public static final String FATAL_LOG_LEVEL_PACKAGES = "common.logger.global.packaglevel.FATAL";
	public static final String OFF_LOG_LEVEL_PACKAGES = "common.logger.global.packaglevel.OFF";

	public static final String ES_DEFAULT_MULE_LOGGING_CODE = "common.logger.mule.default.logging.code";
}
