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

import br.com.vulner.beans.Endereco;
import br.com.vulner.BO.EnderecoBO;

@Path("/endereco")
public class EnderecoResource {

    private final EnderecoBO enderecoBO = new EnderecoBO();

    // Selecionar todos os endereços
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public ArrayList<Endereco> buscar() throws SQLException, ClassNotFoundException {
        return enderecoBO.selecionarBo();
    }

    // Cadastrar novo endereço
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response cadastroRs(Endereco endereco, @Context UriInfo uriInfo) throws SQLException, ClassNotFoundException {
        enderecoBO.inserirBo(endereco);
        UriBuilder builder = uriInfo.getAbsolutePathBuilder();
        builder.path(Integer.toString(endereco.getEnderecoId()));
        return Response.created(builder.build()).build();
    }

    // Atualizar endereço
    @PUT
    @Path("/{enderecoId}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response atualizarRs(Endereco endereco, @PathParam("enderecoId") int enderecoId) throws SQLException, ClassNotFoundException {
        enderecoBO.atualizarBo(endereco);
        return Response.ok().build();
    }

    // Deletar endereço
    @DELETE
    @Path("/{enderecoId}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response deleteRs(@PathParam("enderecoId") int enderecoId) throws SQLException, ClassNotFoundException {
        enderecoBO.deletarBo(enderecoId);
        return Response.ok().build();
    }
}
