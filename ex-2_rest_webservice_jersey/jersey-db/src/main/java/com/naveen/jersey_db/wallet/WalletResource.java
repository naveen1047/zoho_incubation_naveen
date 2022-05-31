package com.naveen.jersey_db.wallet;

import com.naveen.jersey_db.exception.CustomException;
import com.naveen.jersey_db.util.UserUtils;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.HttpHeaders;
import jakarta.ws.rs.core.MediaType;

@Path("wallet")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class WalletResource {

    WalletService walletService;

    public WalletResource() {
        walletService = DependenciesFactory.getWalletService();
    }

    @GET
    @Path("/{id}")
    public Wallet getWallet(@PathParam("id") int id,
                            @HeaderParam(HttpHeaders.AUTHORIZATION) String authorization) {
        if (!UserUtils.compareUserIdAuthId(authorization, String.valueOf(id)))
            throw new CustomException("Id didn't match");
        return walletService.getWallet(id);
    }

    @PUT
    @Path("/{id}")
    public Wallet setWallet(@PathParam("id") int id, Wallet wallet,
                            @HeaderParam(HttpHeaders.AUTHORIZATION) String authorization) {
        if (!UserUtils.compareUserIdAuthId(authorization, String.valueOf(id)))
            throw new CustomException("Id didn't match");
        return walletService.setWallet(id, wallet.getBalance());
    }
}
