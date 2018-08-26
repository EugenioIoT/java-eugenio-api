/*
 * Copyright (c) 2018 Logicalis S/A.
 * 
 * This file is part of java-eugenio-api
 * (see https://github.com/EugenioIoT).
 * 
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 * 
 *   http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
package com.logicalis.eugenio.client.util;

/**
 * Path constants.
 */
public class EugenioPathConstants {

	public static final String PATH_AUTH = "/auth";
	public static final String PATH_USERS = "/users";
	public static final String PATH_USERS_DELETE = "/users/{userId}";
	public static final String PATH_GROUPS = "/users/groups";
	public static final String PATH_SCHEMAS = "/schemas";
	public static final String PATH_SCHEMAS_BY_NAME = "/schemas/{schemaName}";
	public static final String PATH_THINGS = "/things";
	public static final String PATH_THINGS_THINGID = "/things/{deviceId}";
	public static final String PATH_THINGS_INVOKE = "/things/{deviceId}/invoke";
	public static final String PATH_INGESTION = "/data";
	public static final String PATH_QUERY = "/data/query";

}
