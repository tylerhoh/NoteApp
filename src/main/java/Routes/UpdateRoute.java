package Routes;

import DTO.NoteObject;
import DTO.Response;

public class UpdateRoute extends RouteObject{
    public UpdateRoute(String noteID, String body){
        try{
            // INSERT NOTE INTO DATABASE COMMAND
            NoteObject note = noteDatabase.updateNote(noteID, body);

            // IF DONE SUCCESSFULLY RETURN SUCCESSFUL RESPONSE
            localRes = new Response.Builder()
                    .set_id(note._id)
                    .addResponse(note)
                    .setCode("Note updated successfully.")
                    .build();
        }
        catch(Exception e){
            localRes = new Response.Builder()
                    .setCode("ERROR: Failed to store note.")
                    .build();
        }
    }

    public Response create(){
        return localRes;
    }
}
