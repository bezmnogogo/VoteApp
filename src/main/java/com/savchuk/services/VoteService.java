package com.savchuk.services;

import com.savchuk.dao.repository.IVoteRepository;
import com.savchuk.dao.entitties.Vote;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

/**
 * Created by home on 07.03.17.
 */
@Service
@Component
public class VoteService {
    private final IVoteRepository voteRepository;

    @Autowired
    public VoteService(IVoteRepository voteRepository) {
        this.voteRepository = voteRepository;
    }



    public void saveVote(Vote vote){
        voteRepository.save(vote);
    }
}
