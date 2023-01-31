package duke.parsers;
import duke.commands.*;
import duke.dukeexceptions.DukeExceptions;
import duke.dukeexceptions.MissingArgumentException;

public class Parser {
    public static Command parse(String fullCommand) throws DukeExceptions{
        String[] splitStr = fullCommand.split(" ");
        String requestContent = fullCommand.split(" ", 2).length == 2
                ? fullCommand.split(" ", 2)[1]
                : "";

        //END
        if(splitStr[0].equals("bye")) {
            return new EndCommand();
        }

        switch(splitStr[0]) {
        case "list":
            return new ListCommand();

        case "mark":
            if(requestContent.trim() == ""){
                throw new MissingArgumentException("The index cannot be empty.");
            }
            int markIndex = Integer.parseInt(splitStr[1]) - 1;
            return new MarkCommand(markIndex);

        case "unmark":
            if(requestContent.trim() == ""){
                throw new MissingArgumentException("The index cannot be empty.");
            }
            int unmarkIndex = Integer.parseInt(splitStr[1]) - 1;
            return new UnmarkCommand(unmarkIndex);

        case "todo":
            return new ToDoCommand(requestContent);

        case "deadline":
            return new DeadlineCommand(requestContent);

        case "event":
            return new EventCommand(requestContent);

        case "delete":
            if(requestContent.trim() == ""){
                throw new MissingArgumentException("The index cannot be empty.");
            }
            int index = Integer.parseInt(requestContent.trim()) - 1;
            return new DeleteCommand(index);

        default:
            return new UnknownCommand();
        }
    }
}
