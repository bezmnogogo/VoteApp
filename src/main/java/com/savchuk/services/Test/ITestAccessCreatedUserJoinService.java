package com.savchuk.services.Test;

import com.savchuk.dao.entitties.Test.TestAccessCreatedUserJoin;
import com.savchuk.dao.entitties.Test.TestAccessUserJoin;

/**
 * Created by home on 17.05.17.
 */
public interface ITestAccessCreatedUserJoinService {

    TestAccessCreatedUserJoin saveUserAccess(TestAccessCreatedUserJoin testAccessCreatedUserJoin);

    TestAccessCreatedUserJoin getTestAccessCreatedUserJoin(long userId, long testId);

}
