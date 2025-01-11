import { ConversationUserDto } from "./ConversationUserDto";
import { MessageDto } from "./MessageDto";

export interface ConversationDto {
    sender: ConversationUserDto;
    receiver: ConversationUserDto;
    subject: string;
    messages: Array<MessageDto>;
}