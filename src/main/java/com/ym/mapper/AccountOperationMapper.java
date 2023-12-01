package com.ym.mapper;

import com.ym.dto.AccountOperationResponseDTO;
import com.ym.model.AccountOperation;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Component
public class AccountOperationMapper {

    public AccountOperationResponseDTO toAccountOperationResponseDTO(AccountOperation accountOperation){
        AccountOperationResponseDTO accountOperationResponseDTO = new AccountOperationResponseDTO();
        BeanUtils.copyProperties(accountOperation, accountOperationResponseDTO);
        return accountOperationResponseDTO;
    }
}
