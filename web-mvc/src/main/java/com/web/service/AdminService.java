package com.web.service;

import com.web.common.ErrorException;
import org.springframework.stereotype.Service;

@Service
public class AdminService {

    public void getAdmin() {
        throw new ErrorException(ErrorException.ErrorEnum.parameter_error);
    }

}
