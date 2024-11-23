package br.com.vulner.resource;

import java.sql.SQLException;
import java.util.ArrayList;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;
import javax.ws.rs.core.UriInfo;

import br.com.vulner.beans.Fatura;
import br.com.vulner.BO.FaturaBO;

@Path("/fatura")
public class FaturaResource {

    private final FaturaBO faturaBO = new FaturaBO();

    // Selecionar todas as faturas
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public ArrayList<Fatura> buscar() throws SQLException, ClassNotFoundException {
        return faturaBO.selecionarBo();
    }

    // Cadastrar nova fatura
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response cadastroRs(Fatura fatura, @Context UriInfo uriInfo) throws SQLException, ClassNotFoundException {
        faturaBO.inserirBo(fatura);
        UriBuilder builder = uriInfo.getAbsolutePathBuilder();
        builder.path(Integer.toString(fatura.getFaturaId()));
        return Response.created(builder.build()).build();
    }

    // Atualizar fatura
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/{faturaId}")
    public Response atualizarRs(Fatura fatura, @PathParam("faturaId") int faturaId) throws SQLException, ClassNotFoundException {
        faturaBO.atualizarBo(fatura);
        return Response.ok().build();
    }

    // Deletar fatura
    @DELETE
    @Path("/{faturaId}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response deleteRs(@PathParam("faturaId") int faturaId) throws SQLException, ClassNotFoundException {
        faturaBO.deletarBo(faturaId);
        return Response.ok().build();
    }
}
