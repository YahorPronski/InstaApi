import TextArea from "./fields/TextArea";
import {useState} from "react";
import "../../assets/styles/components/form/postform.scss"
import FileUpload from "./fields/FileUpload";
import SubmitButton from "./fields/SubmitButton";

const PostForm = () => {
    const [file, setFile] = useState({});
    const [description, setDescription] = useState('');

    return (
        <div className="post-form">
            <FileUpload
                label="Image"
                onChange={(event) => setFile(event.target.files[0])}
                required/>
            <TextArea
                label="Description"
                name="description"
                value={description}
                onChange={(event) => setDescription(event.target.value)}/>
            <SubmitButton text="Save"/>
        </div>
    );
};

export default PostForm;