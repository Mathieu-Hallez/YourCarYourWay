export class Session {
    private email : string;
    private token : string;

	constructor($email: string, $token: string) {
		this.email = $email;
        this.token = $token
	}

    /**
     * Getter $email
     * @return {string}
     */
	public get $email(): string {
		return this.email;
	}

    /**
     * Setter $email
     * @param {string} value
     */
	public set $email(value: string) {
		this.email = value;
	}

    /**
     * Getter $token
     * @return {string}
     */
	public get $token(): string {
		return this.token;
	}

    /**
     * Setter $token
     * @param {string} value
     */
	public set $token(value: string) {
		this.token = value;
	}

}