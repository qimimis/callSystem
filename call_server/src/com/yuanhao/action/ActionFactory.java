package com.yuanhao.action;

import com.yuanhao.action.impl.*;
import com.yuanhao.dto.ActionEnum;
import com.yuanhao.dto.ClientEnum;

public class ActionFactory {


    public static IAction getAction(ClientEnum client, ActionEnum action) {
        if (client == ClientEnum.CALL_CLIENT && action == ActionEnum.CALL_LOGIN) {
            return new CallerLogin();
        }
        if (client == ClientEnum.CALL_CLIENT && action == ActionEnum.CALL_NEXT) {
            return new CallerNext();
        }
        if (client == ClientEnum.CALL_CLIENT && action == ActionEnum.CALL_RECALL) {
            return new CallerRecall();
        }
        if (client == ClientEnum.CALL_CLIENT && action == ActionEnum.CALL_FAIL) {
            return new CallerFail();
        }
        if (client == ClientEnum.CALL_CLIENT && action == ActionEnum.CALL_SUCCESS) {
            return new CallerSuccess();
        }
        if (client == ClientEnum.TAKE_CLIENT && action == ActionEnum.TAKE_TICKET) {
            return new ClientTicket();
        }
        if (client == ClientEnum.TAKE_CLIENT && action == ActionEnum.GET_BUSINESS) {
            return new ClientGetBusiness();
        }
        return null;
    }
}
