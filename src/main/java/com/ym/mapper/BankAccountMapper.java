package com.ym.mapper;

import com.ym.dto.CurrentAccountRequestDTO;
import com.ym.dto.CurrentAccountResponseDto;
import com.ym.dto.SavingAccountRequestDTO;
import com.ym.dto.SavingAccountResponseDTO;
import com.ym.model.CurrentAccount;
import com.ym.model.SavingAccount;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class BankAccountMapper {

    private CustomerMapper customerMapper;

    public CurrentAccount toCurrentAccount(CurrentAccountRequestDTO currentAccountRequestDTO){
        CurrentAccount currentAccount = new CurrentAccount();
        BeanUtils.copyProperties(currentAccountRequestDTO, currentAccount);
        return currentAccount;
    }

    public CurrentAccountResponseDto toCurrentAccountResponseDTO(CurrentAccount currentAccount){
        CurrentAccountResponseDto currentAccountResponseDto = new CurrentAccountResponseDto();
        BeanUtils.copyProperties(currentAccount, currentAccountResponseDto);
        currentAccountResponseDto.setCustomerResponseDTO(
                customerMapper.toCustomerResponseDTO(currentAccount.getCustomer())
        );
        return currentAccountResponseDto;
    }

    public SavingAccount toSavingAccount(SavingAccountRequestDTO savingAccountRequestDTO){
        SavingAccount savingAccount = new SavingAccount();
        BeanUtils.copyProperties(savingAccountRequestDTO, savingAccount);
        return savingAccount;
    }

    public SavingAccountResponseDTO toSavingAccountResponseDTO(SavingAccount savingAccount){
        SavingAccountResponseDTO savingAccountResponseDTO = new SavingAccountResponseDTO();
        BeanUtils.copyProperties(savingAccount, savingAccountResponseDTO);
        savingAccountResponseDTO.setCustomerResponseDTO(
                customerMapper.toCustomerResponseDTO(savingAccount.getCustomer())
        );
        return savingAccountResponseDTO;
    }
}
