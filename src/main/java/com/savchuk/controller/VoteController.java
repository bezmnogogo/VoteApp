package com.savchuk.controller;

import com.savchuk.dao.entitties.Variant;
import com.savchuk.dao.entitties.Vote;
import com.savchuk.services.VariantService;
import com.savchuk.services.VoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by home on 07.03.17.
 */
@RestController
@RequestMapping(value = "/vote")
public class VoteController {

    private final VoteService voteService;
    private final VariantService variantService;

    @RequestMapping(value = "/")
    Vote vote() {
        Vote vote = new Vote();
        vote.setQuestion("how much: 7 + 6 = ");
        List<Variant> variants = new ArrayList<Variant>();
        variants.add(new Variant("13", 0));
        variants.add(new Variant("14", 0));
        vote.setVariants(variants);
        for(Variant variant: variants)
            variant.setVote(vote);
        vote.generateLink();
        voteService.saveVote(vote);
        return vote;
    }

    @Autowired
    public VoteController(VoteService voteService, VariantService variantService) {
        this.voteService = voteService;
        this.variantService = variantService;
    }

    @RequestMapping(value = "createVote", method = RequestMethod.POST)
    public Vote create(@RequestBody Vote vote){
        voteService.saveVote(vote);
        return vote;
    }
}
