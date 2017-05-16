package com.savchuk.services.Test;

import com.savchuk.dao.entitties.Test.TestAccessUserJoin;
import org.springframework.stereotype.Service;

/**
 * Created by home on 17.05.17.
 */
public interface ITestAccessUserJoinService {

    TestAccessUserJoin addUserAccess(TestAccessUserJoin testAccessUserJoin);

}
