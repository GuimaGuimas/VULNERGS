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

import br.com.vulner.beans.EmpresaCliente;
import br.com.vulner.BO.EmpresaClienteBO;

@Path("/empresaCliente")
public class EmpresaClienteResource {

    private final EmpresaClienteBO empresaClienteBO = new EmpresaClienteBO();

    // Selecionar todas as empresas clientes
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public ArrayList<EmpresaCliente> buscar() throws SQLException, ClassNotFoundException {
        return empresaClienteBO.selecionarBo();
    }

    // Cadastrar nova empresa cliente
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response cadastroRs(EmpresaCliente empresaCliente, @Context UriInfo uriInfo) throws SQLException, ClassNotFoundException {
        empresaClienteBO.inserirBo(empresaCliente);
        UriBuilder builder = uriInfo.getAbsolutePathBuilder();
        builder.path(Integer.toString(empresaCliente.getEmpresaClienteId()));
        return Response.created(builder.build()).build();
    }

    // Atualizar empresa cliente
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/{empresaClienteId}")
    public Response atualizarRs(EmpresaCliente empresaCliente, @PathParam("empresaClienteId") int empresaClienteId) throws SQLException, ClassNotFoundException {
        empresaClienteBO.atualizarBo(empresaCliente);
        return Response.ok().build();
    }

    // Deletar empresa cliente
    @DELETE
    @Path("/{empresaClienteId}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response deleteRs(@PathParam("empresaClienteId") int empresaClienteId) throws SQLException, ClassNotFoundException {
        empresaClienteBO.deletarBo(empresaClienteId);
        return Response.ok().build();
    }
}
