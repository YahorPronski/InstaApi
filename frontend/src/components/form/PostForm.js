import {useState} from "react";
import TextArea from "./fields/TextArea";
import FileUpload from "./fields/FileUpload";
import SubmitButton from "./fields/SubmitButton";
import * as PostService from "../../services/postService";
import "../../assets/styles/components/form/postform.scss"


const PostForm = () => {
    const [file, setFile] = useState({});
    const [description, setDescription] = useState('');

    const handleSubmit = () => {
        PostService.savePost({
            file: file,
            description: description,
        });
    };

    const handleFileUpload = (event) => {
        const reader = new FileReader();
        reader.readAsDataURL(event.target.files[0]);
        reader.onload = function() {
            setFile(reader.result);
        };
    };

    return (
        <form className="post-form">
            <FileUpload
                label="Image"
                onChange={handleFileUpload}
                required/>
            <TextArea
                label="Description"
                name="description"
                value={description}
                onChange={(event) => setDescription(event.target.value)}/>
            <SubmitButton text="Save" onClick={handleSubmit}/>
        </form>
    );
};

export default PostForm;