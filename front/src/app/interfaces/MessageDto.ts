export interface MessageDto {
    id : number;
    parent_id : number;
    text : string;
    is_read : boolean;
    sender_email : string;
    receiver_email : string;
    created_at : string;
}