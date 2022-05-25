package com.naveen.jersey_db.wallet;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
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
    public Wallet getWallet(@PathParam("id") int id) {
        return walletService.getWallet(id);
    }

    @PUT
    @Path("/{id}")
    public Wallet setWallet(@PathParam("id") int id, Wallet wallet) {
        return walletService.setWallet(id, wallet.getBalance());
    }
}
