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

import br.com.vulner.beans.PessoaCliente;
import br.com.vulner.BO.PessoaClienteBO;

@Path("/pessoaCliente")
public class PessoaClienteResource {

    private final PessoaClienteBO pessoaClienteBO = new PessoaClienteBO();

    // Selecionar todas as pessoas clientes
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public ArrayList<PessoaCliente> buscar() throws SQLException, ClassNotFoundException {
        return pessoaClienteBO.selecionarBo();
    }

    // Cadastrar nova pessoa cliente
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response cadastroRs(PessoaCliente pessoaCliente, @Context UriInfo uriInfo) throws SQLException, ClassNotFoundException {
        pessoaClienteBO.inserirBo(pessoaCliente);
        UriBuilder builder = uriInfo.getAbsolutePathBuilder();
        builder.path(Integer.toString(pessoaCliente.getPessoaClienteId()));
        return Response.created(builder.build()).build();
    }

    // Atualizar pessoa cliente
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/{pessoaClienteId}")
    public Response atualizarRs(PessoaCliente pessoaCliente, @PathParam("pessoaClienteId") int pessoaClienteId) throws SQLException, ClassNotFoundException {
        pessoaClienteBO.atualizarBo(pessoaCliente);
        return Response.ok().build();
    }

    // Deletar pessoa cliente
    @DELETE
    @Path("/{pessoaClienteId}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response deleteRs(@PathParam("pessoaClienteId") int pessoaClienteId) throws SQLException, ClassNotFoundException {
        pessoaClienteBO.deletarBo(pessoaClienteId);
        return Response.ok().build();
    }
}
