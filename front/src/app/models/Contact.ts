export class Contact {
    private firstName : string;
    private lastname : string;
    private email : string;

	constructor($firstName: string, $lastname: string, $email: string) {
		this.firstName = $firstName;
		this.lastname = $lastname;
		this.email = $email;
	}

    /**
     * Getter $firstName
     * @return {string}
     */
	public get $firstName(): string {
		return this.firstName;
	}

    /**
     * Getter $lastname
     * @return {string}
     */
	public get $lastname(): string {
		return this.lastname;
	}

    /**
     * Getter $email
     * @return {string}
     */
	public get $email(): string {
		return this.email;
	}

    /**
     * Setter $firstName
     * @param {string} value
     */
	public set $firstName(value: string) {
		this.firstName = value;
	}

    /**
     * Setter $lastname
     * @param {string} value
     */
	public set $lastname(value: string) {
		this.lastname = value;
	}

    /**
     * Setter $email
     * @param {string} value
     */
	public set $email(value: string) {
		this.email = value;
	}

}