export interface NotificationMessage {
    id: number;
    parent: number;
    text: string;
    is_read: boolean;
    sender: string;
    receiver: string;
    type: string;
    created_at: string;
}