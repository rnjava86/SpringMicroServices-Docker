
/*
 * Licensed to the Evoke Technologies under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The Evoke Application  licenses this file to You under the Evoke License, Version 1.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *      http://www.evoketechnologies.org/licenses/LICENSE-1.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.evoke.user.web.controller;

import org.apache.commons.lang.StringUtils;
import org.evoke.user.model.BaseRequest;
import org.evoke.user.model.BaseResponse;
import org.evoke.user.model.LoginRequest;
import org.evoke.user.model.UserDetails;
import org.evoke.user.service.UserServiceImpl;
import org.evoke.user.web.error.ErrorCode;
import org.evoke.user.web.error.ErrorDescription;
import org.evoke.user.web.error.ErrorType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**********************************************************************************
 * Name : UserController.java Desc : This is class is used for User actions
 * Error: Modification : Version Name Date Desc Initial UserController.java
 * 05-Nov-2018 User Controller
 * 
 ***********************************************************************************/

@RestController
@RequestMapping("/user")
public class UserController {

	@Autowired
	UserServiceImpl userService;

	@GetMapping("/check")
	public String check() {
		return "successful";
	}

	@PostMapping(value = "/register")
	public BaseResponse add(@RequestBody LoginRequest request) {

		BaseResponse response = null;
		if (null != request && null != request.getUserDetails()) {

			response = userService.registerUser(request.getUserDetails());

		} else {

			response = new BaseResponse();
			response.setErrorCode(ErrorCode.USER_DETAILS_OBJECT_NOT_FOUND);
			response.setErrorDesc(ErrorDescription.USER_EMIAL_EXIST);
			response.setErrorType(ErrorType.APPLICATION_PRACTICE_ERROR);
			return response;
		}

		return response;

	}

	@PostMapping(value = "/getUser")
	public BaseResponse getUser(@RequestBody LoginRequest request) {

		BaseResponse response = null;
		if (null != request && null != request.getUserDetails()) {

			response = userService.getUser(request.getUserDetails().getId());
		} else {

			response = new BaseResponse();
			response.setErrorCode(ErrorCode.USER_NOT_FOUND);
			response.setErrorDesc(ErrorDescription.USER_NOT_FOUND);
			response.setErrorType(ErrorType.APPLICATION_PRACTICE_ERROR);
			return response;
		}

		return response;

	}

	@PostMapping(value = "/login")
	public BaseResponse loginUser(@RequestBody LoginRequest request) {

		
		BaseResponse response = null;
		if (null != request && null != request.getUserDetails()) {

			response = userService.userLogin(request.getUserDetails());

		} else if (null != request.getUserDetails().getEmail()
				&& StringUtils.isNotEmpty(request.getUserDetails().getEmail())) {

			response = new BaseResponse();
			response.setErrorCode(ErrorCode.EMAIL_NOT_VALID);
			response.setErrorDesc(ErrorDescription.USER_EMAIL_NOT_PROVIDED);
			response.setErrorType(ErrorType.APPLICATION_PRACTICE_ERROR);

		} else {

			response = new BaseResponse();
			response.setErrorCode(ErrorCode.USER_NOT_FOUND);
			response.setErrorDesc(ErrorDescription.USER_NOT_FOUND);
			response.setErrorType(ErrorType.APPLICATION_PRACTICE_ERROR);

		}

		return response;

	}

}
