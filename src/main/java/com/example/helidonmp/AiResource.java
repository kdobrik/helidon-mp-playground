package com.example.helidonmp;

import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Path("/ai")
@RequestScoped
public class AiResource {

    public static class CompletionRequest {
        public String prompt;
    }

    public static class CompletionResponse {
        public String completion;

        public CompletionResponse() {}

        public CompletionResponse(String completion) {
            this.completion = completion;
        }
    }

    private final AiService aiService;

    @Inject
    public AiResource(AiService aiService) {
        this.aiService = aiService;
    }

    @POST
    @Path("/complete")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public CompletionResponse complete(CompletionRequest request) {
        String result = aiService.completeEcho(request == null ? null : request.prompt);
        return new CompletionResponse(result);
    }
}


